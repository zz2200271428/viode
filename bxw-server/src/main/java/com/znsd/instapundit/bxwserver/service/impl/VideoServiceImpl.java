package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.bxwserver.dao.SpecialColumnDao;
import com.znsd.instapundit.bxwserver.dao.VideoDao;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.VideoParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Component
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private SpecialColumnDao specialColumnDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result updateStatus(Integer id, Integer video_status) {
        int result = videoDao.updateStatus(id, video_status);
        return Result.success(CodeMsg.UPDATE_SUCCESS);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result delete(Integer[] ids) {
        int result = videoDao.delete(ids);
        return Result.success(CodeMsg.DELETE_SUCCESS);
    }

    @Override
    public VideoBean getVideoByID(Integer id) {
        return videoDao.getVideoByID(id);
    }

    @Override
    public List<VideoBean> getVideosBySpeID(Integer id) {
        return videoDao.getVideosBySpeID(id);
    }

    @Override
    public PagingResult pagingVideoList(VideoParam param) {
        List<VideoBean> videoList = videoDao.pagingVideoList(param);
        int count = videoDao.getPagingVideoCount(param);
        return PagingResult.success(count, videoList);
    }

    @Override
    public PagingResult pagingApplyList(VideoParam param) {
        List<VideoBean> videoList = videoDao.pagingApplyList(param);
        int count = videoDao.getPagingApplyCount(param);
        return PagingResult.success(count, videoList);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result updateApplyStatus(Integer id, Integer check_status) {
        int result = videoDao.updateApplyStatus(id, check_status);
        return Result.success(CodeMsg.UPDATE_SUCCESS);
    }

    @Override
    public boolean isCollect(Integer videoID, Integer userID) {
        int count = 0;
        VideoBean video = videoDao.getVideoByID(videoID);
        Integer columnID = video.getFrom_sc();
        if (columnID != 0) { // 专栏视频
            count = videoDao.isColumnCollect(columnID, userID);
        } else {
            count = videoDao.isCollect(videoID, userID);
        }
        return count > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean collect(Integer videoID, Integer userID, boolean flag) {
        int count = 0;
        VideoBean video = videoDao.getVideoByID(videoID);
        Integer columnID = video.getFrom_sc();
        if (flag) {
            if (columnID != 0) {
                count = videoDao.insertColumnCollect(columnID, userID);
            } else {
                count = videoDao.insertCollect(videoID, userID);
            }
        } else {
            if (columnID != 0) {
                count = videoDao.deleteColumnCollect(columnID, userID);
            } else {
                count = videoDao.deleteCollect(videoID, userID);
            }
        }
        if (count == 0) {
            throw new GlobalException(CodeMsg.COLLECT_ERROR);
        }
        return flag;
    }

    // 用户视频支付业务
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result pay(Integer videoID, Integer userID) {
        // 操作记录成功的条数
        int count = 0;
        // 获得订单编号、商户号、交易号
        List<String> nums = getOrderNums();
        // 金额
        BigDecimal money = null;
        // 订单对象
        OrderBean order = null;
        // 获取视频对象
        VideoBean video = videoDao.getVideoByID(videoID);
        // 拿到视频对象所属专栏
        Integer columnID = video.getFrom_sc();
        // 视频所属专栏对象
        SpecialColumnBean column = null;
        // 生成order，插入购买记录
        if (columnID != 0) { // 专栏视频
            column = specialColumnDao.getSpecByID(columnID);
            money = column.getPrice();
            order = new OrderBean(userID, nums.get(0), column.getCover_img(), column.getSpe_title(), column.getPrice(), 3, nums.get(1), nums.get(2));
        } else { // 非专栏视频
            money = video.getPrice();
            order = new OrderBean(userID, nums.get(0), video.getCover_img(), video.getVideo_title(), video.getPrice(), 3, nums.get(1), nums.get(2));
        }
        // 尝试支付
        try {
            // 买主减钱
            count = videoDao.minusMoney(userID, money);
            // 买主加钱
            count = videoDao.addMoney(video.getUpload_by().getId(), money);

        } catch (Exception e) { // 如果余额不够支付会抛出检查异常，代表余额不足
            throw new GlobalException(CodeMsg.MONEY_NOT_SUFFICIENT);
        }
        // 如果尝试支付成功，进行下面的操作
        if (count > 0) {
            // 插入购买记录
            if (columnID != 0) {
                count = videoDao.insertColumnPay(userID, columnID);
            } else {
                count = videoDao.insertVideoPay(userID, videoID);
            }
            // 插入订单记录
            count = videoDao.insertOrder(order);
        } else {
            throw new GlobalException(CodeMsg.PAY_ERROR);
        }
        return Result.success(CodeMsg.PAY_SUCCESS);
    }

    @Override
    public boolean isPay(Integer videoID, Integer userID) {
        int count = 0;
        // 获取视频对象
        VideoBean video = videoDao.getVideoByID(videoID);
        AppUserBean upload_by = video.getUpload_by();// 视频所属者
        if (upload_by.getId().equals(userID)) {
            return true;
        }
        // 拿到视频对象所属专栏
        Integer columnID = video.getFrom_sc();
        if (columnID != 0) { // 专栏视频
            count = videoDao.isPayColumn(columnID, userID);
        } else { // 非专栏视频
            count = videoDao.isPayVideo(videoID, userID);
        }
        return count > 0 ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public int insertColumn(SpecialColumnBean column) {
        return videoDao.insertColumn(column);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public int insertVideo(VideoBean video) {
        return videoDao.insertVideo(video);
    }

    @Override
    public int addPlayCount(Integer videoID) {
        return videoDao.addPlayCount(videoID);
    }

    @Override
    public List<VideoBean> getVideoByUserID(Integer userID) {
        return videoDao.getVideoByUserID(userID);
    }

    // 生成订单编号、商户号、交易号
    private List<String> getOrderNums() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
        String time = sdf.format(date);
        String order_number = time + getRandom(4); // 订单号
        String commer = "25" + time + getRandom(2); // 商户号
        String deal = "21" + time + getRandom(2); // 交易号
        return Arrays.asList(order_number, commer, deal);
    }

    // 获取随机数
    public String getRandom(int len) {
        return String.valueOf(Math.random()).substring(2, len + 2);
    }

}

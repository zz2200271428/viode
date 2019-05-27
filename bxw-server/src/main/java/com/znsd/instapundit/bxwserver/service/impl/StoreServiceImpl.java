package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bxwserver.dao.StoreDao;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.StoreParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result updateCheckStatus(Integer storeID, Integer check_status) {
        if (check_status == 2) {
            StoreBean store = storeDao.getStoreApplyByID(storeID);
            int result = storeDao.updateCheckStatus(storeID, check_status);
            int insertResult = storeDao.insertStore(store);
            if ((result + insertResult) != 2) {
                throw new GlobalException(CodeMsg.UPDATE_ERROR);
            }
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        } else if (check_status == 3) {
            int result = storeDao.updateCheckStatus(storeID, check_status);
            return result <= 0 ? Result.success(CodeMsg.UPDATE_ERROR) : Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.PARAM_ERROR);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result delete(Integer[] ids) {
        Integer result = storeDao.delete(ids);
        return Result.success(CodeMsg.DELETE_SUCCESS);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result updateStatus(Integer storeID, Integer status) {
        Integer result = storeDao.updateStatus(storeID, status);
        return Result.success(CodeMsg.UPDATE_SUCCESS);
    }

    @Override
    public PagingResult pagingStoreList(StoreParam param) {
        List<StoreBean> storeList = storeDao.pagingStoreList(param);
        Integer count = storeDao.getPagingStoreCount(param);
        return PagingResult.success(count, storeList);
    }

    @Override
    public StoreBean getStoreByID(Integer storeID) {
        return storeDao.getStoreByID(storeID);
    }

    @Override
    public List<StoreBean> getStoreByActive(Integer active) {
        return storeDao.getStoreByActive(active);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean homepageInit(Integer userID, Integer storeID) {
        storeDao.updateClickCount(storeID);
        Integer count = storeDao.getCollectCount(userID, storeID);
        return count > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean collect(Integer userID, Integer storeID, boolean flag) {
        int count = 0;
        if (flag) {
            // 插入收藏数据
            count = storeDao.insertCollect(userID, storeID);
        } else {
            // 移除收藏数据
            count = storeDao.deleteCollect(userID, storeID);
        }
        if (count < 1) {
            throw new GlobalException(CodeMsg.COLLECT_ERROR);
        }
        return flag;
    }

    @Override
    public PagingResult pagingApplyList(StoreParam param) {
        List<StoreBean> storeList = storeDao.pagingApplyList(param);
        Integer count = storeDao.getPagingApplyCount(param);
        return PagingResult.success(count, storeList);
    }
}

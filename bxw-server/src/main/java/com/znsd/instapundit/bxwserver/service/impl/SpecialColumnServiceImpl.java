package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bxwserver.dao.SpecialColumnDao;
import com.znsd.instapundit.param.SpecialColumnParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.SpecialColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
public class SpecialColumnServiceImpl implements SpecialColumnService {

    @Autowired
    private SpecialColumnDao specialColumnDao;

    @Override
    public PagingResult pagingList(SpecialColumnParam param) {
        List<SpecialColumnBean> specialColumnList = specialColumnDao.pagingList(param);
        int count = specialColumnDao.getPagingCount(param);
        return PagingResult.success(count, specialColumnList);
    }

    // 传播行为 REQUIRED（如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中）
    // 隔离级别 READ_COMMITTED（允许从已经提交的并发事务读取。可防止脏读，但幻影读和不可重复读仍可能会发生。）
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result delete(Integer[] ids) {
        int result = specialColumnDao.delete(ids);
        specialColumnDao.clearVideoSpe(ids);
        return Result.success(CodeMsg.DELETE_SUCCESS);
    }

    @Override
    public List<SpecialColumnBean> mycolumn(Integer userID) {
        return specialColumnDao.getColumnByUserID(userID);
    }

    @Override
    public List<SpecialColumnBean> getListByUserID(Integer userID) {
        return specialColumnDao.getColumnByUserID(userID);
    }

}

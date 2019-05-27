package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.bxwserver.dao.DictDao;
import com.znsd.instapundit.param.DictParam;
import com.znsd.instapundit.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public List<DictBean> listPage(DictParam dictParam) {
        return dictDao.listPage(dictParam);
    }

    @Override
    public List<DictBean> getListType(String type) {
        return dictDao.getListType(type);
    }

    @Override
    public int addDict(DictBean dictBean) {
        return dictDao.addDict(dictBean);
    }

    @Override
    public DictBean getDictById(Integer id) {
        return dictDao.getDictById(id);
    }

    @Override
    public int updateDict(DictBean dictBean) {
        return dictDao.updateDict(dictBean);
    }

    @Override
    public int delete(Integer[] ids) {
        return dictDao.delete(ids);
    }

    @Override
    public List<DictBean> getLabel() {
        return dictDao.getLabel();
    }

    @Override
    public int getCount(DictParam dictParam) {
        return dictDao.getCount(dictParam);
    }


}

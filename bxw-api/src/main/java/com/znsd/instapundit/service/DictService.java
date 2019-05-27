package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.param.DictParam;

import java.util.List;

public interface DictService {

    int addDict(DictBean dictBean);

    DictBean getDictById(Integer id);

    int updateDict(DictBean dictBean);

    int delete(Integer[] ids);

    List<DictBean> getLabel();

    int getCount(DictParam dictParam);

    List<DictBean> listPage(DictParam dictParam);

    List<DictBean> getListType(String type);

}

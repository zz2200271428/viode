package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.param.SpecialColumnParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;

import java.util.List;

public interface SpecialColumnService {

    PagingResult pagingList(SpecialColumnParam param);

    Result delete(Integer[] ids);

    List<SpecialColumnBean> mycolumn(Integer userID);

    List<SpecialColumnBean> getListByUserID(Integer userID);
}

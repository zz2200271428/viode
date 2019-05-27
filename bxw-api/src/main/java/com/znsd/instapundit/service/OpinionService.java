package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.OpinionBean;
import com.znsd.instapundit.param.OpinionParam;

import java.util.List;

public interface OpinionService {

    List<OpinionBean> listPage(OpinionParam opinionParam);

    int getCount(OpinionParam opinionParam);
}

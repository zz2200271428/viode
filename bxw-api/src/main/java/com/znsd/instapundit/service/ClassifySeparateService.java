package com.znsd.instapundit.service;

import com.znsd.instapundit.param.ClassifySeparateParam;
import com.znsd.instapundit.result.PagingResult;

public interface ClassifySeparateService {
    PagingResult listPage(ClassifySeparateParam classifySeparateParam);
}

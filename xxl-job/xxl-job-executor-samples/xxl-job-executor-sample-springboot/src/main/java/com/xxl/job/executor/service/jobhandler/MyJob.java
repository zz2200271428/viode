package com.xxl.job.executor.service.jobhandler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.znsd.instapundit.service.OrderService;
import org.springframework.stereotype.Component;

@JobHandler(value="MyJob")
@Component
public class MyJob extends IJobHandler {

    @Reference
    private OrderService orderService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {



        System.out.println("111111"+orderService);
        return SUCCESS;
    }
}

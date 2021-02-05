package com.yeqifu.bus.aop;

import com.yeqifu.bus.vo.DubboServiceDo;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopTestClass {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    public void beforeMethod(JoinPoint jp) {
        logger.info(System.currentTimeMillis()+"");
        System.out.println(System.currentTimeMillis());
    }
}

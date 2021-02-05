package com.yeqifu.bus.service.consumerProvider;

import com.yeqifu.bus.vo.DubboServiceDo;
import com.yeqifu.sys.cache.CachePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestPointCutService {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    public void read(String stuName) {
        if(CachePool.CACHE_CONTAINER.containsKey(stuName)) {
            DubboServiceDo bean = (DubboServiceDo)CachePool.CACHE_CONTAINER.get(stuName);
            logger.info(bean.getStuName());
        } else {
            logger.info("沒拿到");
        }
    }
}

package com.yeqifu.bus.service.impl;

import com.yeqifu.bus.mapper.DubboServiceDao;
import com.yeqifu.bus.service.consumerProvider.TestPointCutService;
import com.yeqifu.bus.vo.DubboServiceDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DubboServiceImpl {

    @Autowired
    DubboServiceDao dubboServiceDao;

    @Autowired
    TestPointCutService testPointCutService;

    public int insertBean(DubboServiceDo dubboService) {
        dubboServiceDao.insert(dubboService);

        return 0;
    }

    public void testPoint() {
        testPointCutService.read("customer:Kwaui");
    }

    public int updateByStuName(DubboServiceDo dubboService) {
        return dubboServiceDao.updateServiceByStatus(dubboService);
    }
}

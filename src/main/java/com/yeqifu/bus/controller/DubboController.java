package com.yeqifu.bus.controller;

import com.yeqifu.bus.mapper.DubboServiceDao;
//import com.yeqifu.bus.service.consumerProvider.ExtractBeanInfoService;
import com.yeqifu.bus.service.impl.DubboServiceImpl;
import com.yeqifu.bus.vo.DubboServiceDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Autowired
    DubboServiceDao dubboServiceDao;

    @Autowired
    DubboServiceImpl dubboServiceImpl;

//    @Autowired
////    ExtractBeanInfoService extractBeanInfoService;

    @PostMapping("I1Insert")
    public void I1(@RequestBody DubboServiceDo dubboService) {
        dubboServiceImpl.insertBean(dubboService);
    }

    @PostMapping("I1read")
    public void I1Read(@RequestBody DubboServiceDo dubboService) {
        dubboServiceImpl.testPoint();
    }


    @PostMapping("/I2update")
    public void I2(DubboServiceDo dubboService) {
        dubboServiceImpl.updateByStuName(dubboService);
    }

//    @PostMapping("/consumer")
//    public void consumer() {
//        extractBeanInfoService.process("Kwaui");
//    }
}

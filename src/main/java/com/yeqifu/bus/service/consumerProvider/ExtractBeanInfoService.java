//package com.yeqifu.bus.service.consumerProvider;
//
//import com.yeqifu.bus.vo.DubboServiceDo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.LinkedBlockingQueue;
//
//@Component
//public class ExtractBeanInfoService {
//    private LinkedBlockingQueue<DubboServiceDo> consumerQueue = new LinkedBlockingQueue<>();
//    ProviderStru providerStru;
//
//    @Autowired
//    public void setProviderStru(ProviderStru providerStru) {
//        this.providerStru = providerStru;
//    }
//
//    public void process(String name) {
//        ConsumerStru consumerStru = new ConsumerStru(name, consumerQueue);
//        providerStru.setBean("Kwaui");
//        providerStru.setQueue(consumerQueue);
//        providerStru.run();
//    }
//}

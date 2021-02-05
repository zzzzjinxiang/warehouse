//package com.yeqifu.bus.service.consumerProvider;
//
//import com.yeqifu.bus.vo.DubboServiceDo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class ConsumerStru {
//    LinkedBlockingQueue<DubboServiceDo> consumerQueue;
//    DubboServiceDo dubboService;
//    Logger logger = LoggerFactory.getLogger(ConsumerStru.class.getName());
//
//    private String driver;
//    AtomicInteger i = new AtomicInteger(0);
//
//    public ConsumerStru(String name, LinkedBlockingQueue<DubboServiceDo> consumerQueue) {
//        System.out.println(driver);
//        this.consumerQueue = consumerQueue;
//        dubboService = new DubboServiceDo();
//        dubboService.setStuName(name);
//    }
//
//    public void consume() {
//        while(i.get()<10) {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                logger.error("error", e);
//            }
//            DubboServiceDo bean = consumerQueue.poll();
//            if(ObjectUtils.isEmpty(bean)) {
//                logger.info("还未开始消费");
//                break;
//            }
//            logger.info("成绩："+bean.getStuScore());
//            i.incrementAndGet();
//        }
//    }
//}

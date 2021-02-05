//package com.yeqifu.bus.service.consumerProvider;
//
//import com.yeqifu.bus.mapper.DubboServiceDao;
//import com.yeqifu.bus.vo.DubboServiceDo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.LinkedBlockingQueue;
//
//@Component
//public class ProviderStru implements Runnable {
//    Logger logger = LoggerFactory.getLogger(ProviderStru.class.getName());
//    private DubboServiceDo bean = new DubboServiceDo();
//    ConsumerStru consumerStru;
//    LinkedBlockingQueue<DubboServiceDo> providerQueue;
//    DubboServiceDao dubboServiceDao;
//
//    @Autowired
//    public void setDubboServiceDao(DubboServiceDao dubboServiceDao) {
//        this.dubboServiceDao = dubboServiceDao;
//    }
//
//    public void setQueue(LinkedBlockingQueue<DubboServiceDo> providerQueue) {
//        this.providerQueue = providerQueue;
//    }
//
//    public ProviderStru(ConsumerStru consumerStru) {
//        this.consumerStru = consumerStru;
//    }
//
//    public void setBean(String name) {
//        bean.setStuName(name);
//    }
//
//    public void executeTask() {
//        List<DubboServiceDo> beanList = dubboServiceDao.selectByName(bean.getStuName());
//        logger.info("查询到bean");
//        for(DubboServiceDo bean:beanList) {
//            logger.info("开始入队");
//            providerQueue.offer(bean);
//        }
//        run();
//        return;
//    }
//
//    @Override
//    public void run() {
//        executeTask();
//    }
//}

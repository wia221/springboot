package org.spring.springboot.controller;

import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.spring.springboot.entity.TimeTaskSchedulCenterBean;
import org.spring.springboot.service.TimeTaskSchedulCenterService;
import org.spring.springboot.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;




@RestController
@RequestMapping("/time/")
public class TimeController {
    private Logger logger = Logger.getLogger(TimeController.class);
    @Autowired
    TimeTaskSchedulCenterService timeTaskSchedulCenterService;
    //手动事务支持
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    @RequestMapping(value = "findTimeTask.do" ,method = RequestMethod.POST)
    public Page<TimeTaskSchedulCenterBean> findListById(HashMap<String,Object> map){
      //  map.put("id","e4ac6868c51a11e8ada200163e0e3d11");
        PageHelper.startPage(1,5);
        Page<TimeTaskSchedulCenterBean>  taskSchedulCenterBeans = timeTaskSchedulCenterService.findListById(map);
        return taskSchedulCenterBeans;
    }

    @RequestMapping(value = "findTimeTaskOne.do" ,method = RequestMethod.POST)
    public TimeTaskSchedulCenterBean findById(String id){
        id = "e4ac6868c51a11e8ada200163e0e3d11";
        return timeTaskSchedulCenterService.findById(id);
    }
    /**
     * 制作XSSF
     */
    @RequestMapping(value = "dowExc.do" ,method = RequestMethod.POST)
    public void downlondExc(HashMap<String,Object> map) throws IOException {
        PageHelper.startPage(1,10);
        Page<TimeTaskSchedulCenterBean>  taskSchedulCenterBeans = timeTaskSchedulCenterService.findListById(map);
        if(timeTaskSchedulCenterService.downlondExc(taskSchedulCenterBeans)){
            logger.info("Excel生成成功........");
        }
    }

    /**
     * 加入事务支持
     * propagation 事务的传播行为，默认值为 REQUIRED。
     * isolation 事务的隔离度，默认值采用 DEFAULT
     * timeout 事务的超时时间，默认值为-1，不超时。如果设置了超时时间(单位秒)，那么如果超过该时间限制了但事务还没有完成，则自动回滚事务。
     * read-only 指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。*
     * rollbackFor 用于指定能够触发事务回滚的异常类型，如果有多个异常类型需要指定，各类型之间可以通过逗号分隔。{xxx1.class, xxx2.class,……}
     * noRollbackFor 抛出 no-rollback-for 指定的异常类型，不回滚事务。{xxx1.class, xxx2.class,……}
     * @param bean
     * @return
     */
    //@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @RequestMapping(value = "updateTime.do",method = RequestMethod.POST)
    public int updateTime(TimeTaskSchedulCenterBean bean){
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        int isUpdate = 0;
        try{
            String b = "asd";
            int a = Integer.parseInt(b);
            isUpdate = timeTaskSchedulCenterService.updateById(bean);
            if(isUpdate != 0){
                logger.info("timeController-------->updateTime success");
            }
        }catch (Exception e){
            platformTransactionManager.rollback(transactionStatus);//手动回滚
            logger.info("timeController-------->updateTime failure",e);
        }
        return isUpdate;
    }
}

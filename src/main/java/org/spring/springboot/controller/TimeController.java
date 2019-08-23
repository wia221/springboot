package org.spring.springboot.controller;

import com.github.pagehelper.PageHelper;
import org.spring.springboot.entity.TimeTaskSchedulCenterBean;
import org.spring.springboot.util.Page;
import org.spring.springboot.service.TimeTaskSchedulCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/time/")
public class TimeController {

    @Autowired
    TimeTaskSchedulCenterService timeTaskSchedulCenterService;

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
            System.out.printf("Excel生成成功........");
        }
    }
}

package org.spring.springboot.service;

import org.spring.springboot.entity.TimeTaskSchedulCenterBean;
import org.spring.springboot.util.Page;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 批处理表
 */
public interface TimeTaskSchedulCenterService {
    //查询
    Page<TimeTaskSchedulCenterBean> findListById(HashMap<String,Object> map);

    TimeTaskSchedulCenterBean findById(String id);

    // 下载制作表单
    Boolean downlondExc(List<TimeTaskSchedulCenterBean> list) throws IOException;

    int updateById(TimeTaskSchedulCenterBean bean);
}

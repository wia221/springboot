package org.spring.springboot.service.impl;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.spring.springboot.dao.TimeTaskSchedulCenterDao;
import org.spring.springboot.entity.TimeTaskSchedulCenterBean;
import org.spring.springboot.service.TimeTaskSchedulCenterService;
import org.spring.springboot.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 批处理表实现
 */
@Service
public class TimeTaskSchedulCenterServiceImpl implements TimeTaskSchedulCenterService {

   @Autowired()
    TimeTaskSchedulCenterDao timeTaskSchedulCenterDao;

    //查询
    @Override
    public Page<TimeTaskSchedulCenterBean> findListById(HashMap<String, Object> map) {
        return timeTaskSchedulCenterDao.selectByPrimaryKey(map);
    }

    @Override
    public TimeTaskSchedulCenterBean findById(String id) {
        return timeTaskSchedulCenterDao.selectByPrimaryKeyTest(id);
    }
    //下载制作表单
    @Override
    public Boolean downlondExc(List<TimeTaskSchedulCenterBean> list) throws IOException {
        String[] title = {"ServiceId", "ServiceName","time"};
        //创建HSSF工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个Sheet页
        XSSFSheet sheet = workbook.createSheet();
        //创建第一行（一般是表头）
        XSSFRow row0 = sheet.createRow(0);
        //创建列
        XSSFCell cell = null;
        //设置表头
        for (int i = 0; i < title.length; i++) {
            cell = row0.createCell(i);
            cell.setCellValue(title[i]);
        }
        //填充20行数据
       /* for (int i = 1; i < 20; i++) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(18);
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(12);
        }*/
        int count = 1;
        for (TimeTaskSchedulCenterBean times: list) {
            XSSFRow row = sheet.createRow(count);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(times.getServiceId());
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(times.getServiceName());
            XSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(times.getCreateDatetime());
            count++;
        }
        //保存到本地
        File file = new File("D:/Exc.xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);
        //将Excel写入输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        return true;
    }

}

package org.spring.springboot.test;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class POITest {
    public static void main(String[] args) throws IOException {
        String[] title = {"订单ID", "流水号"};
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
        for (int i = 1; i < 20; i++) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(18);
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(12);
        }
        //保存到本地
        File file = new File("D:/test.xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);
        //将Excel写入输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }
}

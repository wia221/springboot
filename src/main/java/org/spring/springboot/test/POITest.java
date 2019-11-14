package org.spring.springboot.test;

import org.spring.springboot.util.ExcelPoiReaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class POITest {
    public static void main(String[] args) throws IOException {
        String filePath = "E:/媒体统计数据 (每日数据).xls";
        String columns[] = {"媒体ID","日期", "曝光量", "点击量", "点击率","CPC","CPM","预计收益"};
        List<Map<String, String>> list = new ExcelPoiReaderUtils().readExcel(filePath, columns);
        //遍历解析出来的list
        for (Map<String,String> map : list) {
            for (Map.Entry<String,String> entry : map.entrySet()) {
                if ("媒体ID".equals(entry.getKey())) {
                    System.out.println("媒体ID"+entry.getValue());
                    break;
                }
                if("日期".equals(entry.getKey())){
                    System.out.println("日期"+entry.getValue());
                }
                if("曝光量".equals(entry.getKey())){
                    System.out.println("曝光量"+entry.getValue());
                }
                if("点击量".equals(entry.getKey())){
                    System.out.println("点击量"+entry.getValue());
                }
                if("预计收益".equals(entry.getKey())){
                    double expectedAmount = Double.parseDouble(entry.getValue());
                    expectedAmount = Math.round(expectedAmount * 100);//转换分为单位
                    long l = new Double(expectedAmount).longValue();
                    System.out.println("预计收益"+l);

                }
            }
            }
         System.out.println();
        }
    }
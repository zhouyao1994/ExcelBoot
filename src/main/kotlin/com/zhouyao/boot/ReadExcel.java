package com.zhouyao.boot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {
  private static XSSFWorkbook readFile(String filename) throws IOException {
    try (FileInputStream fis = new FileInputStream(filename)) {
      return new XSSFWorkbook(fis);        // NOSONAR - should not be closed here
    }
  }
  public static void main(String[] args) throws IOException {
    String filePath = "/Users/zhouyao/Downloads/CodeDemov1.xlsx";
    XSSFWorkbook workbook = readFile(filePath);
    XSSFSheet sheet1 = workbook.getSheet("Sheet1");
    XSSFCell cell = sheet1.getRow(0).getCell(0);
    System.out.println(cell.toString());
    System.out.println(sheet1.getLastRowNum());
    System.out.println(sheet1.getFirstRowNum());
    System.out.println(sheet1.getRow(0).getFirstCellNum());
    System.out.println(sheet1.getRow(0).getLastCellNum());
    System.out.println();
//    Workbook[] wbs = new Workbook[] { new HSSFWorkbook(), new XSSFWorkbook() };

  }
}

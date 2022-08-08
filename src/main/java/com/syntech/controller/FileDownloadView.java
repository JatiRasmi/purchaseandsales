/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author rasmi
 */
@Named
@RequestScoped
public class FileDownloadView {

    public void downloadFile() throws FileNotFoundException, IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product Empty Data");
        String[] headers = new String[]{"Unit", "Name", "Description"};

        Row r = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            r.createCell(i).setCellValue(headers[i]);
        }

        sheet.getRow(0); 
        //create file at the loaction
        OutputStream fileOut = new FileOutputStream("/home/rasmi/NetBeansProjects/purchaseandsales/src/main/webapp/excelfile/myproduct.xlsx");

        System.out.println("Excel File has been created successfully.");
        workbook.write(fileOut);
    }
}

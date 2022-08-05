/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.controller.ProductController;
import com.syntech.model.Product;
import com.syntech.model.Unit;
import com.syntech.util.MessageUtill;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author rasmi
 */
@RequestScoped
public class ExcelUpload implements Serializable {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @Inject
    private UnitRepository unitRepository;

    @Inject
    private MessageUtill messageUtill;
    
    @Inject
    private ProductRepository productRepository;

    public List<Product> uploadExcelFile(InputStream inputStream)
            throws EncryptedDocumentException, IOException {

        List<Product> productList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() == 0) {
                continue;
            }
            Product prod = new Product();

            Long unitId = Long.valueOf((int) row.getCell(0).getNumericCellValue());
            Unit unit = unitRepository.findById(unitId);
            prod.setUnit(unit);

            prod.setName(row.getCell(1).getStringCellValue());
            prod.setDescription(row.getCell(2).getStringCellValue());

            String name = row.getCell(1).getStringCellValue();

            List<Product> p = productRepository.findByName(name);

            if (p == null) {
                productList.add(prod);
                logger.log(Level.INFO, "Product Selected Successfully!!");
                messageUtill.showInfo("Product File Uploaded Successfully ","Uploaded Successfully!!!!");
            }
            logger.log(Level.WARNING,"Product Already exists");
            messageUtill.showError("Product Already Exists", "Already Exists!!!!");
        }
        return productList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import java.io.File;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author rasmi
 */
@Named
@RequestScoped
public class FileDownloadView {


    public void downloadFile() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Content-disposition", "Attachment;filename=product.xlxs");
            FacesContext.getCurrentInstance().getExternalContext().setResponseContentType("application/xls");
            FileUtils.copyFile(new File("/home/rasmi/NetBeansProjects/purchaseandsales/src/main/webapp/excelfile/product.xlsx"),
                    FacesContext.getCurrentInstance().getExternalContext().getResponseOutputStream());

            FacesContext.getCurrentInstance().responseComplete();
        }catch(IOException e){
            
        }
    }
}
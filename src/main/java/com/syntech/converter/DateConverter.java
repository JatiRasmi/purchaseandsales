///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.converter;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.ConverterException;
//import javax.faces.convert.DateTimeConverter;
//import javax.faces.convert.FacesConverter;
//
///**
// *
// * @author rasmi
// */
//@FacesConverter("dateConverter")
//public class DateConverter extends DateTimeConverter {
//
//    public DateConverter() {
//        setPattern("MM/dd/yyyy");
//    }
//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value != null && value.length() != getPattern().length()) {
//            throw new ConverterException("Invalid format");
//        }
//
//        return super.getAsObject(context, component, value);
//    }
//}


import java.sql.Date;
import java.time.LocalDate;
import javax.faces.convert.FacesConverter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
 
@FacesConverter("dateConverter")
@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {
     
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return locDate == null ? null : Date.valueOf(locDate);
    }
 
    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }
}
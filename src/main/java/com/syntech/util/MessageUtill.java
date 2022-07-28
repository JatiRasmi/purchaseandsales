package com.syntech.util;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class MessageUtill implements Serializable {

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showWarn(String title, String message) {
        addMessage(FacesMessage.SEVERITY_WARN, title, message);
    }

    public void showInfo(String title, String message) {
        addMessage(FacesMessage.SEVERITY_INFO, title, message);
    }

    public void showError(String title, String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, title, message);
    }

    public void showSticky(String title, String message) {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }
    public void setValidationFlag(){
        FacesContext.getCurrentInstance().validationFailed();
    }
}

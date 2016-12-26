/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.wsconfig.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
public class Status {
    private int code;
    private String title, message;

    //Constructors
    public Status(int code) {
        this.code = code;
    }

    public Status(int code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }

    //Getters - Setters
   
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.wsconfig.model;

import java.util.List;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
public class CustomException extends AbstractCustomException{
    public CustomException(javax.ws.rs.core.Response.Status status, String message, List<String> exceptions) {
        super(status,message,exceptions);
    }
    public CustomException(javax.ws.rs.core.Response.Status status, String message, Exception exception) {
        super(status, message, exception);
    }
}

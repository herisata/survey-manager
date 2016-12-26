/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.wsconfig.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
public class InternalServerErrorException  extends AbstractCustomException{
    
    public InternalServerErrorException(String message, List<String> exceptions) {
        super(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR,message,exceptions);
    }
    public InternalServerErrorException(String message, Exception exception) {
        super(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR,message, exception);
    }
}

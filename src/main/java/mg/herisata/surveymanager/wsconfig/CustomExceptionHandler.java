/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.wsconfig;

import mg.herisata.surveymanager.wsconfig.model.AbstractCustomException;
import mg.herisata.surveymanager.wsconfig.model.BadRequestException;
import mg.herisata.surveymanager.wsconfig.model.CustomException;
import mg.herisata.surveymanager.wsconfig.model.InternalServerErrorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@Provider
public class CustomExceptionHandler implements ExceptionMapper<Exception> 
{   
    @Override
    public Response toResponse(Exception exception) 
    {
        Logger.getLogger(CustomExceptionHandler.class.getName()).log(Level.SEVERE, null, exception);
        if(exception instanceof AbstractCustomException){
            return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(exception).build();
        }else{
            if(exception instanceof javax.ws.rs.NotFoundException){
                return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(new CustomException(Status.NOT_FOUND, Status.NOT_FOUND.getReasonPhrase(), exception)).build();
            }else if(exception instanceof javax.ws.rs.BadRequestException
                    ||exception instanceof com.fasterxml.jackson.databind.JsonMappingException){
                return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(new BadRequestException(Status.BAD_REQUEST.getReasonPhrase(), exception)).build();
            }
            else return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(new InternalServerErrorException(null, exception)).build();
        }
        //return Response.status(Status.BAD_REQUEST).entity(exception).build();  
    }
}

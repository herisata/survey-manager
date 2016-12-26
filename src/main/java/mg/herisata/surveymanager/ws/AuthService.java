/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.ws;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mg.herisata.surveymanager.entity.TUser;
import mg.herisata.surveymanager.wsconfig.JwtUtil;
import mg.herisata.surveymanager.wsconfig.WsUtil;
import mg.herisata.surveymanager.wsconfig.model.BadRequestException;
import mg.herisata.surveymanager.wsconfig.model.JsonRequest;
import mg.herisata.surveymanager.wsconfig.model.JsonResponse;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@Stateless
@Path("auth")
public class AuthService {
    @PersistenceContext
    EntityManager mEntityManager;
    /*
    @Resource
    private UserTransaction mUserTransaction;
    */
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResponse doLogin(JsonRequest request) throws BadRequestException {
        JsonResponse response=new JsonResponse();
        TUser user=null;
        
        //Verification de l'objet request
        try {
            WsUtil.verifyRequest(request, "login","password");
        } catch (Exception ex) {
            throw new BadRequestException(null, ex);
        }
        //Si request correct
        HashMap<String,Object> payload=request.getPayload();
        String login=(String)payload.get("login");
        String password=(String)payload.get("password");
        
        //Get user from DB
        Query query = mEntityManager.createQuery("FROM TUser WHERE login =:login AND password =:password")
                .setParameter("login", login)
                .setParameter("password", WsUtil.toSha256(password));//
        try{
            user = (TUser) query.getSingleResult();
        }catch(Exception e){
            throw new BadRequestException("Login/password incorrect.", e);
        }
        //If user fetched
        //create token
        Map tokenPayload=new HashMap();
        tokenPayload.put("user-id", user.getId());
        String token=JwtUtil.generateToken("Token of user "+user.getLogin(), tokenPayload);
        response.getPayload().put("token", token);
        
        //Return user info
        response.getPayload().put("user-info", user);
        
        return response;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.wsconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import mg.herisata.surveymanager.wsconfig.model.JsonRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import mg.herisata.surveymanager.wsconfig.model.BadRequestException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.netbeans.rest.application.config.ApplicationConfig;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
public class WsUtil {
 
    /*New utility functions */
    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static DateFormat getDateFormat() {
        return DF;
    }
    public static ObjectMapper getObjectMapper(){
        return new JacksonConfig().getContext(WsUtil.class);
    }
    public static String toSha256(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(WsUtil.class.getName()).log(Level.SEVERE, null, ex);
            //Should never happen
        }
        return null;
    }
    public static <E> E updateIgnoreNull(E oldEntity, E newEntity) {
        Field[] newEntityFields = newEntity.getClass().getDeclaredFields();
        Hashtable newHT = fieldsToHT(newEntityFields, newEntity);

        Class oldEntityClass = oldEntity.getClass();
        Field[] oldEntityFields = oldEntityClass.getDeclaredFields();

        for (Field field : oldEntityFields){
            if(field.getName().contains("serialVersionUID")) continue;
            field.setAccessible(true);
            Object o = newHT.get(field.getName());
            if (o != null){
                try {
                    Field f = oldEntityClass.getDeclaredField(field.getName());
                    f.setAccessible(true);
                    f.set(oldEntity, o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        return oldEntity;
    }



    private static Hashtable<String, Object> fieldsToHT(Field[] fields, Object obj){
        Hashtable<String,Object> hashtable = new Hashtable<>();
        for (Field field: fields){
            field.setAccessible(true);
            try {
                Object retrievedObject = field.get(obj);
                if (retrievedObject != null){
                    hashtable.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return hashtable;
    }
    public static void verifyRequest(JsonRequest request, String... fields) throws BadRequestException{
        String msg="Incorrect request data";
        ArrayList list=new ArrayList();
        if(request==null) throw new BadRequestException(msg,(Exception)null);
        if(request.getPayload()==null) throw new BadRequestException(msg+": No 'payload'",(Exception)null);
        for(String field:fields){
            if(request.getPayload().get(field)==null) list.add(field);
            else if(field.equalsIgnoreCase("token")){
                String token=(String)request.getPayload().get("token");
                try {
                    if(ApplicationConfig.validateToken()) JwtUtil.readToken(token);
                } catch (InvalidJwtException ex) {
                    throw new BadRequestException("Incorrect token", ex);
                }
            }
        }
        if(!list.isEmpty())throw new BadRequestException(msg+": Field(s) "+list.toString()+" required",(Exception)null);
    }
    public static void verifyFieldsNotNull(Object obj, String... fieldNames) throws Exception{
        ArrayList<String> fieldsNotSet=new ArrayList<>();
        for(String fieldName:fieldNames){
            try {
                Field field = obj.getClass().getField(fieldName);
                if(field.get(obj)==null){
                    fieldsNotSet.add(fieldName);
                }
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(WsUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(WsUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(WsUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(WsUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!fieldsNotSet.isEmpty()) throw new Exception("Fields "+fieldsNotSet+" required");
    }
    /*
    public static String saveImage(String imageFileName, String base64EncodedImage) throws IOException {
        if(base64EncodedImage==null) base64EncodedImage="";
        byte[] data = Base64.getDecoder().decode(base64EncodedImage);
        File directory=new File(Constant.C_FILE_IMAGES_PATH);
        if(!directory.isDirectory()) directory.mkdirs();
        if(imageFileName==null) imageFileName="image_"+new Date().getTime();
        try (OutputStream stream = new FileOutputStream(Constant.C_FILE_IMAGES_PATH+imageFileName)) {
            stream.write(data);
        }
        return imageFileName;
    }
    */
}

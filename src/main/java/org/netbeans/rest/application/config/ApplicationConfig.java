/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    private static final boolean CFG_DEBUG_ENABLED=true;
    private static final boolean CFG_TOKEN_VALIDATE=true;

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    public static boolean debug(){
        return CFG_DEBUG_ENABLED;
    }
    public static boolean validateToken(){
        return CFG_TOKEN_VALIDATE;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(mg.herisata.surveymanager.ws.AuthService.class);
        resources.add(mg.herisata.surveymanager.ws.SurveyService.class);
        resources.add(mg.herisata.surveymanager.wsconfig.CORSFilter.class);
        resources.add(mg.herisata.surveymanager.wsconfig.CustomExceptionHandler.class);
        resources.add(mg.herisata.surveymanager.wsconfig.JacksonConfig.class);
    }
}

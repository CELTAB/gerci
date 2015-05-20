package br.org.celtab.gerci.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.org.celtab.gerci.provider.MyJacksonJsonProvider;
import br.org.celtab.gerci.resource.TestService;
import br.org.celtab.gerci.resource.TransformacaoService;
/**
 * 
 * 
 *
 */
@ApplicationPath("/service")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        
        Set<Class<?>> resources = new java.util.HashSet<>();          
        
       resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        
       //Adiciona as classes que respondem pelas chamadas de serviço
       resources.add(MyJacksonJsonProvider.class);
       resources.add(TestService.class);
       resources.add(TransformacaoService.class);
        
       return resources;
    }
    
    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }
    
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.wadl.disableWadl", true);
        
        return properties;
    }    
}

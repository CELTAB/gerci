package main.java.br.org.celtab.gerci.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import main.java.br.org.celtab.gerci.provider.MyJacksonJsonProvider;
import main.java.br.org.celtab.gerci.resource.TransformacaoService;

/**
 * 
 * @author Mohamad Abu Ali <arabian@brasnet.org>
 * @author Thiago R. M. Bitencourt <thiago.mbitencourt@gmail.com>
 *
 * Classe de Aplicação, que registra as classes que irão responder aos serviços. Registra o caminho da aplicação para /service.
 * Todas as classes implementadas no pacote resource, que respondem por um caminho e/ou por um serviço devem ser registradas nesta classe.
 */
@ApplicationPath("/service")
public class ApplicationConfig extends Application {

	// Reimplementação do método getClasses, para registrar as classes de serviço
    @Override
    public Set<Class<?>> getClasses() {
        
    	//Variavel resources contem o mapeamento das classes que respondem por serviços.
       Set<Class<?>> resources = new java.util.HashSet<>();          
       
       // Adiciona o Jackson, que será responsavel pelo parser de JSON para as Classes modelo.
       resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        
       //Adiciona as classes que respondem pelas chamadas de serviço
       resources.add(MyJacksonJsonProvider.class);
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

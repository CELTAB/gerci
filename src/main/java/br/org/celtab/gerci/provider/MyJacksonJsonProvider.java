package main.java.br.org.celtab.gerci.provider;
 
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson JSON processor could be controlled via providing a custom Jackson ObjectMapper instance. 
 * This could be handy if you need to redefine the default Jackson behavior and to fine-tune how 
 * your JSON data structures look like (copied from Jersey web site). * 
 * @see https://jersey.java.net/documentation/latest/media.html#d0e4799
 */

/**
 *
 * @author Mohamad Abu Ali <arabian@brasnet.org>
 * @author Thiago R. M. Bitencourt <thiago.mbitencourt@gmail.com>
 *
 */
@Provider
public class MyJacksonJsonProvider implements ContextResolver<ObjectMapper> {
	
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static {
      MAPPER.setSerializationInclusion(Include.NON_EMPTY);
      MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
    }
 
    public MyJacksonJsonProvider() {
    }
     
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return MAPPER;
    } 
}
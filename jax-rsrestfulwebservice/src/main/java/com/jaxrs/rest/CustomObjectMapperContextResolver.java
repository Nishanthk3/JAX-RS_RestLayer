package com.jaxrs.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

//import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomObjectMapperContextResolver implements
        ContextResolver<ObjectMapper> {
    private ObjectMapper mapper = null;
    
    public CustomObjectMapperContextResolver() {
        super();
        mapper = new ObjectMapper().configure(
                SerializationConfig.Feature.WRAP_ROOT_VALUE, true).configure(
                DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true)
                .setSerializationInclusion(Inclusion.NON_NULL);
    }
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}

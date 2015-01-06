package com.jaxrs.rest;

import javax.xml.bind.annotation.*;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.*;

//import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "sampleObject")
@JsonRootName(value = "sampleObject")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name  = "SampleObject", propOrder = {
		"id",
		"name"
})

public class SampleObject 
{
    protected String id;
    protected String name;

    public SampleObject()
    {
    }

    public SampleObject(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
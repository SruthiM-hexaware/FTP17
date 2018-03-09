package com.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello Sruthi";
    }
   /* 
    @GET
    @Path("/Sruthi")
    @Produces(MediaType.TEXT_PLAIN)
    public String getName() {
        return "Sruthi M Nair";
    }*/

    @GET
    @Path("/EvenOdd/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayEvenOdd(@PathParam("i") int i)
    {
        if(i%2==0)
        {
            return "even number";
        } else
        {
           return  "odd number";
        }
    }
    
    @GET
    @Path("/Positivenegative/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayPositiveNegative(@PathParam("i") int i)
    {
        if(i > 0)
        {
            return "Positive number";
        } else if (i < 0)
        {
           return  "Negative number";
        } else {
            return "number is neither positive or negative";
        }
    }
    @GET
    @Path("/Sum/{i}/{j}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sum(@PathParam("i") int i, @PathParam("j") int j)
    {
        int k= i+j;
        return "Sum is" + k;
    }
     @GET
    @Path("/Sub/{i}/{j}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sub(@PathParam("i") int i, @PathParam("j") int j)
    {
        int k= i-j;
        return "diff is" + k;
    }
}

package com.rest;
import javax.ws.rs.PathParam;

import java.util.Arrays;

import javax.annotation.Generated;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("demo")
public class CallcDemo {

    @GET
    @Path("/sum/{i}/{k}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sum(@PathParam ("i") int i, @PathParam ("k") int k) {
        int c= i+k;
        return "Sum is :" +c;
    }

    @GET
    @Path("/Mul/{j}/{k}")
    @Produces(MediaType.APPLICATION_JSON)
    public String Mul(@PathParam ("j") int j, @PathParam ("k") int k) {
        int l = j*k;
        return "Product is : " + l;
    }
    @GET
    @Path("/div/{k}/{l}")
    @Produces(MediaType.APPLICATION_JSON)
    public String div( @PathParam ("k") int k, @PathParam ("l") int l) {
        int t = k/l;
        return " Quotient is : " + t;
    }

    @GET
    @Path("/Square/{j}")
    @Produces(MediaType.APPLICATION_JSON)
    public String Square(@PathParam ("j") int j) { 
        int h = j*j;
        return " Square of " + j + " is " + h;
    }
    @GET
    @Path("/Table/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    public newrest[] Table(@PathParam ("i") int i) {
    
        newrest [] cl = newrest.numbers(i);
        return cl;
    }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public final Employee[] employeesList() {
      System.out.println("Employees List");
      final Employee[] employees = Employee.listAll();
      return employees;
    }
   
}
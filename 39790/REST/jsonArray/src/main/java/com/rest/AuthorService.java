package com.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/AuthorService")
public class AuthorService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
     @GET
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public List<Person> getTrackInJSON() {
List<Person> listPerson = new ArrayList<Person>();
Person p1 = new Person();
p1.setId(1);;
p1.setName("vaishali");
Person p2 = new Person();
p2.setId(2);
p2.setName("sruthi");
listPerson.add(p1);
listPerson.add(p2);
return listPerson;
}
}
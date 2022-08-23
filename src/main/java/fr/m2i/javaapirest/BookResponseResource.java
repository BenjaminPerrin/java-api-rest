/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest;


import java.util.Arrays;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/response-books")
public class BookResponseResource {

    // URI : /ok/without-response
    @GET
    @Path("/ok/without-response")
    public String getBookWithoutResponse() {
        System.out.println("Endpoint : getBookWithoutResponse");
        return "Java 8 doc";
    }

    // URI : /ok
    @GET
    @Path("/ok")
    public Response getBook() {
        System.out.println("Endpoint : getBook");
        return Response.status(Response.Status.OK).entity("Java 8 doc").build();
    }

    
    // URI : /ok/headers
    @GET
    @Path("/ok/headers")
    public Response getBookWithHeaders() {
        System.out.println("Endpoint : getBookWithHeaders");
        return Response.status(Response.Status.OK).entity("Java 8 doc").header("param", "value").build();
    }
    
   // URI : /ok/json-produces
    @GET
    @Path("/ok/json-produces")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookJSONProduces() {
        System.out.println("Endpoint : getBookJSONProduces");

        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("9-999999-99");

        return book;
    }

    // URI : /ok/json
    @GET
    @Path("/ok/json")
    public Response getBookJSON() {
        System.out.println("Endpoint : getBookJSON");

        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("9-999999-99");

        return Response.status(Response.Status.OK).entity(book).type(MediaType.APPLICATION_JSON).build();
    }
    
    // URI : /error/webapp-exception
    @GET
    @Path("/error/webapp-exception")
    public String getBookByIdWithWebAppException(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookByIdWithWebAppException");

        if (id == null) {
            throw new BadRequestException();
        }

        return "Livre avec id : " + id + " - Java 8 doc";
    }

    // URI : /error
    @GET
    @Path("/error")
    public Response getBookByIdWithError(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookByIdWithError");

        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).entity("Livre avec id : " + id + " - Java 8 doc").build();
    }
}
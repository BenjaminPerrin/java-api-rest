/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest;


import java.util.Arrays;
import java.util.List;
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
import javax.ws.rs.core.UriInfo;

@Path("/content-books")
public class BookContentResource {

    
    // URI : /books/
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Book getContentBookXml() {
        System.out.println("Endpoint : getContentBookXml");
        Book book = new Book();
        book.setName("harry potter");
        book.setIsbn("1-111111-11");
        return book;
    }
    
    @PUT
    @Path("/xml")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateContentBookXml(Book book) {
        System.out.println("Endpoint : updateContentBookXml");
        System.out.println("les modifications "+ book.getName() + " "+ book.getIsbn() +" apportées");

    }

       // URI : content-books/json
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getContentBookJSON() {
        System.out.println("Endpoint : getContentBookJSON");

        Book book = new Book();
        book.setName("JSON Potter");
        book.setIsbn("9-999999-99");

        return book;
    }

    // URI : content-books/json
    @PUT
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContentBookJSON(Book book) {
        System.out.println("Endpoint : updateContentBookJSON");
        System.out.println("Les modifications apportées - name : " +
                book.getName() + ", isbn : " + book.getIsbn());
    }
    
       // URI : content-books/jsonxml
    @GET
    @Path("/jsonxml")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getContentBookJSONAndXML() {
        System.out.println("Endpoint : getContentBookJSONAndXML");

        Book book = new Book();
        book.setName("JSON or xml Potter");
        book.setIsbn("9-999999-99");

        return book;
    }
    
       // URI : content-books/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllContentBookJSON() {
        System.out.println("Endpoint : getAllContentBookJSON");

        Book book1 = new Book();
        book1.setName("JSON Potter");
        book1.setIsbn("9-999999-99");
        
        Book book2 = new Book();
        book2.setName("harry Potter");
        book2.setIsbn("1-111111-11");
        
        Book book3 = new Book();
        book3.setName("marry Potter");
        book3.setIsbn("1-2222222-11");

        return Arrays.asList(book1, book2, book3);
    }
}
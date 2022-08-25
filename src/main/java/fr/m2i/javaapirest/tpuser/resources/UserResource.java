/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest.tpuser.resources;


import fr.m2i.javaapirest.*;
import fr.m2i.javaapirest.tpuser.dao.UserDAO;
import fr.m2i.javaapirest.tpuser.model.User;
import fr.m2i.javaapirest.tpuser.util.SessionHelper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
public class UserResource {
    
    // URI : /
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@Context HttpServletRequest request) {
        System.out.println("Endpoint : getUsers");
        SessionHelper.getEntityManager();
        
        UserDAO users = new UserDAO();

        if (users == null) {
            System.out.println("users null : "+users);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ArrayList()).build();
        }
        return Response.status(Response.Status.OK).entity(users.getUsers()).build();
    }
    
    // URI : /
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id,@Context HttpServletRequest request) {
        System.out.println("Endpoint : getUserById");
        UserDAO users = new UserDAO();
        User user = users.findById(id);
        // Vérifie le paramètre 'id' -> bad request si invalide
        if (id == null || id < 1L || user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User was not found").build();
        }
        SessionHelper.getEntityManager();
        User userFind = users.findById(id);
        if (userFind == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("An error occurred").build();
        }
        return Response.status(Response.Status.OK).entity(userFind).build();
    }
  
    
    // URI : /
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user, @Context HttpServletRequest request) {
        System.out.println("Endpoint : create");

        UserDAO users = new UserDAO();

        // Dans le cas où mon users est null, je l'instancie
        if (users == null) {
            users = new UserDAO();
        }

        // Ajout de la personne dans la liste
        boolean Created = users.create(user);
        if (Created == false) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error occurred").build();
        }

        return Response.status(Response.Status.OK).entity("User successfully created").build();
    }

    
    // URI : /1
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, User user, @Context HttpServletRequest request) {
        System.out.println("Endpoint : update");

        // Vérifie le paramètre 'id' -> bad request si invalide
        if (id == null || id < 1L) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User was not found").build();
        }

        UserDAO users = new UserDAO();
        String notFoundError = String.format("La personne avec l'id: %d n'existe pas", id);

        // L'annuaire n'est pas encore créer -> personne not found
        if (users == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error occurred").build();
        }

        boolean success = users.update(id, user);
        if (success) {
            return Response.status(Response.Status.OK).entity("User successfully modified").build();
        }
        if (!success) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error occurred").build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // URI : /1
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id, @Context HttpServletRequest request) {
        System.out.println("Endpoint : delete");
        UserDAO users = new UserDAO();
        User user = users.findById(id);
        // Vérifie le paramètre 'id' -> bad request si invalide
        if (id == null || id < 1L || user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User was not found").build();
        }


        boolean success = users.delete(user);

        if (!success) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error occurred").build();
        }

        return Response.status(Response.Status.OK).entity(" User successfully deleted").build();
    }
    
    // URI : /
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("q") String q,@QueryParam("count") int count,@Context HttpServletRequest request) {
        System.out.println("Endpoint : search");
        UserDAO users = new UserDAO();

        // Vérifie le paramètre 'count' et 'q' -> bad request si invalide
        if (q == null || count < 1) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("An error occurred").build();
        }
        SessionHelper.getEntityManager();
        List<User> userSearch = users.search(q, count);
        if (userSearch == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("An error occurred").build();
        }
        if (userSearch.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No user found").build();
        }
        return Response.status(Response.Status.OK).entity(userSearch).build();
    }
}
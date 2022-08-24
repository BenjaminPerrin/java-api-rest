/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest;


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

@Path("/personnes")
public class AnnuaireResource {

    // URI : /
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Personne createPersonne(Personne personne, @Context HttpServletRequest request) {
        System.out.println("Endpoint : createPersonne");

        // Récupérer l'annuaire stocké dans les attributs de la Session
        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");

        // Dans le cas où mon annuaire est null, je l'instancie
        if (annuaire == null) {
            annuaire = new AnnuaireDAO();
        }

        // Ajout de la personne dans la liste
        Personne created = annuaire.create(personne);

        // Créer / met à jour mon annuaire en Session
        request.getSession().setAttribute("annuaire", annuaire);

        return created;
    }

    // URI : /
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getPersonnes(@Context HttpServletRequest request) {
        System.out.println("Endpoint : getPersonnes");

        // Récupérer l'annuaire stocké dans les attributs de la Session
        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");

        if (annuaire == null) {
            return new ArrayList();
        }

        return annuaire.getPersonnes();
    }
    
    // URI : /1
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonneById(@PathParam("id") Long id, @Context HttpServletRequest request) {

        // Vérifie le paramètre 'id' -> bad request si invalide
        if (id == null || id < 1L) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le paramètre id est invalide").build();
        }

        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");
        String notFoundError = String.format("La personne avec l'id: %d n'existe pas", id);

        // L'annuaire n'est pas encore créer -> personne not found
        if (annuaire == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundError).build();
        }

        Personne personne = annuaire.getPersonneById(id);

        // Personne non trouvé dans la liste -> personne not found
        if (personne == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundError).build();
        }

        return Response.status(Response.Status.OK).entity(personne).build();
    }

    
    @PUT
    @Path("/personne")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePersonne(Personne personne, @Context HttpServletRequest request) {
        System.out.println("Endpoint : updatePersonne");
        // Récupérer l'annuaire stocké dans les attributs de la Session
        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");
        annuaire.updatePersonne(personne);
        // Créer / met à jour mon annuaire en Session
        request.getSession().setAttribute("annuaire", annuaire);
        System.out.println("les modifications "+ personne.getNom()+ " "+ personne.getPrenom() +" apportées");

    }
    
     // URI : /1
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonne(@PathParam("id") Long id, @Context HttpServletRequest request) {

        // Vérifie le paramètre 'id' -> bad request si invalide
        if (id == null || id < 1L) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le paramètre id est invalide").build();
        }

        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");
        String notFoundError = String.format("La personne avec l'id: %d n'existe pas", id);

        // L'annuaire n'est pas encore créer -> personne not found
        if (annuaire == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundError).build();
        }

        boolean success = annuaire.delete(id);

        if (!success) {
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundError).build();
        }

        return Response.status(Response.Status.OK).entity("La personne a bien été supprimé").build();
    }
}
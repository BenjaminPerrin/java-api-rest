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
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
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
    
    // URI : /
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Personne getPersonneById(@PathParam("id") int id, @Context HttpServletRequest request) {
//        System.out.println("Endpoint : getPersonneById "+id);

        // Récupérer l'annuaire stocké dans les attributs de la Session
        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");

        if (annuaire == null) {
            return null;
        }
        
        return annuaire.getPersonneById(id);
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
    
    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePersonne(Personne personne, @Context HttpServletRequest request) {
        System.out.println("Endpoint : deletePersonne");

        // Récupérer l'annuaire stocké dans les attributs de la Session
        AnnuaireDAO annuaire = (AnnuaireDAO) request.getSession().getAttribute("annuaire");

        // Dans le cas où mon annuaire est null, je l'instancie
        if (annuaire == null) {
            annuaire = new AnnuaireDAO();
        }

        // delete de la personne dans la liste
        annuaire.deletePersonne(personne);

        // Créer / met à jour mon annuaire en Session
        request.getSession().setAttribute("annuaire", annuaire);

    }
}
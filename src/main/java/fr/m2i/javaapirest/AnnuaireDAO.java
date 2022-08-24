/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest;

import java.util.ArrayList;
import java.util.List;

public class AnnuaireDAO {

    private List<Personne> personnes;
    private Long nextId;

    public AnnuaireDAO() {
        this.personnes = new ArrayList();
        this.nextId = 1L;
    }

    public Personne create(Personne personne) {
        personne.setId(nextId);
        personnes.add(personne);

        nextId++;

        return personne;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }
    
    public Personne getPersonneById(int id) {
        for (int i = 0; i < personnes.size(); i++) {
            Personne tmp = personnes.get(i);
            if (tmp.getId() == id) {
                System.out.println("Endpoint : getPersonneById "+tmp);
                return tmp;
            }
        }
        return null;
    }
    
    public void updatePersonne(Personne personne){
        for (int i = 1; i < personnes.size(); i++) {
            Personne tmp = personnes.get(i);
            if (tmp.getId() == personne.getId()) {
                int nb = personnes.indexOf(personnes.get(i));
                System.out.println("Endpoint : updatePersonne "+personne.toString()+ " tmp.getId() "+nb);
                personnes.set(Math.toIntExact(nb), personne);
            }
        }
    }
    
    public void deletePersonne(Personne personne){
        for (int i = 0; i < personnes.size()-1; i++) {
            Personne tmp = personnes.get(i);
            if (tmp.getId() == personne.getId()) {
                int nb = personnes.indexOf(personnes.get(i));
                System.out.println("Endpoint : deletePersonne "+personne.toString() + "nb "+nb);
                personnes.remove(Math.toIntExact(nb));
            }
        }
    }
}

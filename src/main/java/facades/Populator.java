/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MovieDTO;
import entities.Movie;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade fe = MovieFacade.getFacadeExample(emf);
        String[] actors = {"actor1 lastname1", "actor2 lastname2", "actor3 lastname3"};
        String[] actors2 = {"anotherActor1 lastname1", "anotherActor2 lastname2", "anotherActor3 lastname3"};
        fe.create(new MovieDTO(new Movie(1995, "some title", actors)));
        fe.create(new MovieDTO(new Movie(1985, "some other title", actors2)));
        fe.create(new MovieDTO(new Movie(1920, "some new other title", actors2)));
    }
    
    public static void main(String[] args) {
        populate();
    }
}

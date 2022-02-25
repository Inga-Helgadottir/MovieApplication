package facades;

import entities.Movie;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {//int year, String title, String[] actors
        EntityManager em = emf.createEntityManager();
        try {
            String[] actors = {"actor1 lastname1", "actor2 lastname2", "actor3 lastname3"};
            String[] actors2 = {"anotherActor1 lastname1", "anotherActor2 lastname2", "anotherActor3 lastname3"};
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie(1995, "some title", actors));
            em.persist(new Movie(1985, "some other title", actors2));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
    }

    @Test
    void getMovieByTitle() {
        int expected = 1;
        int actual = facade.getMovieByTitle("some title").size();
        assertEquals(actual, expected);
    }
}

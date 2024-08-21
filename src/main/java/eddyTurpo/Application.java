package eddyTurpo;

import eddyTurpo.dao.EventDAO;
import eddyTurpo.entities.Evento;
import eddyTurpo.enums.EventType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EventDAO eventDAO = new EventDAO(em);

        Evento laurea = new Evento("laurea", LocalDate.of(2023, 10, 10), "portate i pasticcini", EventType.PRIVATO, 50);

        eventDAO.save(laurea);

        /*try {
            Evento laureaDB = eventDAO.findByID();
            System.out.println(laureaDB);
        } catch (NotFoundEx e) {
            System.out.println(e.getMessage());
        }

        try {
            eventDAO.delete(1);
        } catch (NotFoundEx e) {
            System.out.println(e.getMessage());
        }*/
        em.close();
        emf.close();
    }
}

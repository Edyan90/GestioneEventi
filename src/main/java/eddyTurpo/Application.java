package eddyTurpo;

import eddyTurpo.dao.EventDAO;
import eddyTurpo.dao.LocationDAO;
import eddyTurpo.entities.Evento;
import eddyTurpo.entities.Location;
import eddyTurpo.enums.EventType;
import eddyTurpo.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.UUID;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EventDAO eventDAO = new EventDAO(em);
        LocationDAO locationDAO = new LocationDAO(em);

        Location location = new Location("Salone Conferenze", "Milano");
        locationDAO.save(location);
        Evento laurea = new Evento("laurea", LocalDate.of(2023, 10, 10), "portate i pasticcini", EventType.PRIVATO, 50, location);

        eventDAO.save(laurea);

        try {
            Evento laureaDB = eventDAO.findByID(UUID.fromString("c1926e8d-f537-4050-a9d1-0ffe23fcabc8"));
            System.out.println(laureaDB);
        } catch (NotFoundEx e) {
            System.out.println(e.getMessage());
        }

        try {
            eventDAO.delete(UUID.fromString("c1926e8d-f537-4050-a9d1-0ffe23fcabc8"));
        } catch (NotFoundEx e) {
            System.out.println(e.getMessage());
        }
        em.close();
        emf.close();
    }
}

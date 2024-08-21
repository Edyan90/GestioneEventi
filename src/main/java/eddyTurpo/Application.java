package eddyTurpo;

import eddyTurpo.dao.EventDAO;
import eddyTurpo.dao.LocationDAO;
import eddyTurpo.dao.PartecipazioneDAO;
import eddyTurpo.dao.PersonaDAO;
import eddyTurpo.entities.Evento;
import eddyTurpo.entities.Location;
import eddyTurpo.entities.Partecipazione;
import eddyTurpo.entities.Persona;
import eddyTurpo.enums.EventType;
import eddyTurpo.enums.SessoType;
import eddyTurpo.enums.StatoType;
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
        PersonaDAO personaDAO = new PersonaDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

        Location location = new Location("Salone Conferenze", "Milano");
        locationDAO.save(location);

        Evento laurea = new Evento("laurea", LocalDate.of(2023, 10, 10), "portate i pasticcini", EventType.PRIVATO, 50, location);
        eventDAO.save(laurea);

        Persona studente = new Persona("Eddy", "Turpo", "edyan7@hotmail.it", LocalDate.of(1990, 04, 01), SessoType.M);
        personaDAO.save(studente);

        Partecipazione festaLaurea = new Partecipazione(studente, laurea, StatoType.CONFERMATA);
        partecipazioneDAO.save(festaLaurea);

        try {
            Evento laureaDB = eventDAO.findByID(UUID.fromString("c1926e8d-f537-4050-a9d1-0ffe23fcabc8"));
            System.out.println(laureaDB);
        } catch (NotFoundEx e) {
            System.out.println(e.getMessage());
        }

        /*try {
            eventDAO.delete(UUID.fromString("c1926e8d-f537-4050-a9d1-0ffe23fcabc8"));
        } catch (NotFoundEx e) {
            System.out.println(e.getMessage());
        }*/
        em.close();
        emf.close();
    }
}

package eddyTurpo.dao;

import eddyTurpo.entities.Evento;
import eddyTurpo.entities.Location;
import eddyTurpo.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class LocationDAO {
    private final EntityManager localDao;

    public LocationDAO(EntityManager localDao) {

        this.localDao = localDao;
    }

    public void save(Location location) {
        EntityTransaction transaction = localDao.getTransaction();
        transaction.begin();
        localDao.persist(location);
        transaction.commit();
        System.out.println("La location con ID:  " + location.getId() + " è stato salvato correttamente");
    }

    public Evento findByID(UUID id) {
        Evento found = localDao.find(Evento.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(UUID id) {
        Evento found = this.findByID(id);
        EntityTransaction transaction = localDao.getTransaction();
        transaction.begin();
        localDao.remove(found);
        transaction.commit();
        System.out.println("l'evento è stato rimosso!");

    }
}

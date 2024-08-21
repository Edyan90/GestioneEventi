package eddyTurpo.entities;

import eddyTurpo.enums.StatoType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "partecipazione")
public class Partecipazione {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "persona")
    private String persona;
    @Column(name = "evento")
    private String evento;
    @Column(name = "stato")
    private StatoType stato;

    public Partecipazione() {

    }

    public Partecipazione(String persona, UUID id, String evento, StatoType stato) {
        this.persona = persona;
        this.evento = evento;
        this.stato = stato;
    }

    public UUID getId() {
        return id;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public StatoType getStato() {
        return stato;
    }

    public void setStato(StatoType stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", persona='" + persona + '\'' +
                ", evento='" + evento + '\'' +
                ", stato=" + stato +
                '}';
    }
}

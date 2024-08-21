package eddyTurpo.exceptions;

import java.util.UUID;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(UUID id) {
        super("l'evento con id " + id + " non è stato trovato");
    }

}

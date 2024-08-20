package eddyTurpo.exceptions;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(long id) {
        super("l'evento con id " + id + " non è stato trovato");
    }

}

package lucacampion.ender.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Il Record con id " + id + " non è stato trovato");
    }

    public NotFoundException(String msg) {super(msg);}
}

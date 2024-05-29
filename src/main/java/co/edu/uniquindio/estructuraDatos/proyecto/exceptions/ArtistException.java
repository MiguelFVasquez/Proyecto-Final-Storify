package co.edu.uniquindio.estructuraDatos.proyecto.exceptions;

public class ArtistException extends Exception{

    /**
     * Metodo que permite enviar mensaje de error mediante una excepcion
     * @param message
     */
    public ArtistException(String message) {
        super(message);
    }
}

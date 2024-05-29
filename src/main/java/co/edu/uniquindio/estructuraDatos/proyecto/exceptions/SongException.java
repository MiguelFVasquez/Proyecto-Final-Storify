package co.edu.uniquindio.estructuraDatos.proyecto.exceptions;

public class SongException extends Exception{

    //Metodo que permite enviar mensaje de error mediante una excepcion
    public SongException(String message) {
        super(message);
    }
}

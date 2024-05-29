package co.edu.uniquindio.estructuraDatos.proyecto.exceptions;

public class UserException extends Exception{

    //Metodo que permite enviar mensaje de error mediante una excepcion
    public UserException(String message) {
        super(message);
    }
}

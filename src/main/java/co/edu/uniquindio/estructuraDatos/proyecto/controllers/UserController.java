package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class UserController {
    public ModelFactoryController mfm;
    public UserController(){
        System.out.println("Llamado a la clase singleton desde UserViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

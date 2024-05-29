package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class UserController {

    //Se instancia el ModelFactoryController
    public ModelFactoryController mfm;

    //Netodo que permite hacer el llamada de la clase singleton
    public UserController(){
        System.out.println("Llamado a la clase singleton desde UserViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

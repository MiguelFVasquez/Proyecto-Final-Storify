package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class LoginController {

    //Se instancia el ModelFactoryController
    public ModelFactoryController mfm;

    //Netodo que permite hacer el llamada de la clase singleton
    public LoginController(){
        System.out.println("Llamado a la clase singleton desde LoginViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

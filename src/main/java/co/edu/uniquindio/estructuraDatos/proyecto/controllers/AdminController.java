package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class AdminController {
    //Se instancia el ModelFactoryController
    public ModelFactoryController mfm;


    //Netodo que permite hacer el llamada de la clase singleton
    public AdminController(){
        System.out.println("Llamado a la clase singleton desde AdminViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class AdminController {
    public ModelFactoryController mfm;
    public AdminController(){
        System.out.println("Llamado a la clase singleton desde AdminViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

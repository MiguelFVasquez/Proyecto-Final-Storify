package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class OptionsController {

    //Se instancia el ModelFactoryController
    public ModelFactoryController mfm;

    //Netodo que permite hacer el llamada de la clase singleton
    public OptionsController(){
        System.out.println("Llamado a la clase singleton desde OptionsController");
        mfm= ModelFactoryController.getInstance();
    }
}

package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class RescuePassController {

    //Se instancia el ModelFactoryController
    public ModelFactoryController mfm;

    //Netodo que permite hacer el llamada de la clase singleton
    public RescuePassController() {
        System.out.println("Llamado a la clase singleton desde RecuePasswordController");
        mfm= ModelFactoryController.getInstance();
    }
}

package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class RescuePassController {
    public ModelFactoryController mfm;
    public RescuePassController() {
        System.out.println("Llamado a la clase singleton desde RecuePasswordController");
        mfm= ModelFactoryController.getInstance();
    }
}

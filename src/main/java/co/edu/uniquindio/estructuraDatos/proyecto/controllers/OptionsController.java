package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class OptionsController {
    public ModelFactoryController mfm;
    public OptionsController(){
        System.out.println("Llamado a la clase singleton desde OptionsController");
        mfm= ModelFactoryController.getInstance();
    }
}

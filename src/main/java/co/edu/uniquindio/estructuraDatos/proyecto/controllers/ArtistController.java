package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class ArtistController {
    public ModelFactoryController mfm;
    public ArtistController(){
        System.out.println("Llamado a la clase singleton desde ArtistViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

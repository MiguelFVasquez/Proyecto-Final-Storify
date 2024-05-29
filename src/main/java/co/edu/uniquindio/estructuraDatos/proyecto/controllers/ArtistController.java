package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class ArtistController {

    //Se crea la instancia de la clase ModelFactoryController
    public ModelFactoryController mfm;

    //Netodo que permite hacer el llamada de la clase singleton
    public ArtistController(){
        System.out.println("Llamado a la clase singleton desde ArtistViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

public class LoginController {
    public ModelFactoryController mfm;
    public LoginController(){
        System.out.println("Llamado a la clase singleton desde LoginViewController");
        mfm= ModelFactoryController.getInstance();
    }
}

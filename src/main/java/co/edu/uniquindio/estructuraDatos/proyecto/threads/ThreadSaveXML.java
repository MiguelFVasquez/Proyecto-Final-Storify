package co.edu.uniquindio.estructuraDatos.proyecto.threads;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.ModelFactoryController;

public class ThreadSaveXML extends Thread{


    @Override
    public void run() {
        ModelFactoryController.saveResourceXML();
    }
}

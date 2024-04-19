package co.edu.uniquindio.estructuraDatos.proyecto.threads;

import co.edu.uniquindio.estructuraDatos.proyecto.controllers.ModelFactoryController;

public class ThreadLoadXML extends Thread{

    @Override
    public void run() {
        ModelFactoryController.loadResourceStorifyXML();
    }
}

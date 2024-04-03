package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

import co.edu.uniquindio.estructuraDatos.proyecto.app.AdminViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.app.UserViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.ArtistViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;

public class ModelFactoryController {
    private LoginViewController loginViewController;
    private UserViewController userViewController;
    private ArtistViewController artistViewController;
    private AdminViewController adminViewController;
    static Storify storify;

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("Invocacion clase singleton");
        initData();
    }

    private void initData(){
        storify = new Storify("SHUHENFY");

        User user1 = new User("Camilo","123","camilo@gmail.com");
    }

    public void initLoginViewController(LoginViewController loginViewController){
        this.loginViewController = loginViewController;
    }
    public void initUserViewController(UserViewController userViewController){
        this.userViewController = userViewController;
    }
    public void initArtistViewController(ArtistViewController artistViewController){
        this.artistViewController = artistViewController;
    }
    public void initAdminViewController(AdminViewController adminViewController){
        this.adminViewController = adminViewController;
    }

    //----------------------- USER FUNCTIONS --------------------------------------------//

    public boolean verifyUser(String userName){
        return storify.verifyUser(userName);
    }

    public boolean verifyPassword(String password){
        return storify.verifyPassword(password);
    }
    public User getUser(String userName){
        return storify.getUser(userName);
    }

}

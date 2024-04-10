package co.edu.uniquindio.estructuraDatos.proyecto.controllers;

import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.ArtistException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.AdminViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.UserViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.exceptions.UserException;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.persistence.Persistence;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.ArtistViewController;
import co.edu.uniquindio.estructuraDatos.proyecto.viewControllers.LoginViewController;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController {
    private LoginViewController loginViewController;
    private UserViewController userViewController;
    private ArtistViewController artistViewController;
    private AdminViewController adminViewController;
    static Storify storify;

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE;

        static {
            try {
                eINSTANCE = new ModelFactoryController();
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() throws UserException {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("Invocacion clase singleton");
        initData();
    }

    private void initData() throws UserException {
        storify = new Storify("SHUHENFY");



        User user1 = new User("Camilo","123","camilo@gmail.com");
        storify.addUser(user1);
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

    public boolean registerUser(String userName, String password, String emial)throws UserException{

        User user = new User(userName,password,emial);
        return storify.addUser(user);
    }

    public boolean verifyUser(String userName){
        return storify.verifyUser(userName);
    }
    public boolean verifyPassword(String password){
        return storify.verifyPassword(password);
    }

    public boolean logInUser(String userName, String password){
        return storify.logIn(userName,password);
    }
    public User getUser(String userName){
        return storify.getUser(userName);
    }

      /*
      Metodo para cargar archivos serializados de cliente
       */

    //-------------------------Admin functions---------------------------------------------------
        //---------------------Artist functions------------------------------------------------

    public List<Artist> getListArtist(){
        return storify.getArtistTree().toList();
    }


    public boolean addArtist(String code, String name, String nationality, boolean isAGroup) throws ArtistException {
        Artist newArtist= new Artist(code,name,nationality,isAGroup);
        return storify.addArtist(newArtist);
    }
    public boolean deleteArtist(Artist deleteArtist) throws ArtistException {
        return storify.deleteArtist(deleteArtist);
    }
    public boolean updateArtist(Artist artistUpdate) throws ArtistException {
        return storify.updateArtist(artistUpdate);
    }

    //------------------------ Serialization functions --------------------------------------//

    public void userSerialization() throws IOException {
        Persistence.saveUsers( storify.getUsersMap() );
    }

}

package co.edu.uniquindio.estructuraDatos.proyecto.model;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.CircularLinkedList;

import java.io.Serializable;

/*Usuario se conoce su username (único), contraseña, email, además el usuario tiene una
lista propia de Canciones, dicha lista es una Lista circular.
* */
public class User implements Serializable {

    private String userName;
    private String password;
    private String email;
    private CircularLinkedList<Song> listaCanciones;

    public User() {
    }
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.listaCanciones = new CircularLinkedList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CircularLinkedList<Song> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(CircularLinkedList<Song> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }
}

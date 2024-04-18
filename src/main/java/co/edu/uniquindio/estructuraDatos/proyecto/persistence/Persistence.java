package co.edu.uniquindio.estructuraDatos.proyecto.persistence;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.BinaryTree;
import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.CircularLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.util.UtilFile;
import co.edu.uniquindio.estructuraDatos.proyecto.utilities.FileUtil;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Persistence {
    public static final String ROUTE_USER_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Users.txt";
    public static final String ROUTE_ARTIST_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Artist.txt";
    public static final String ROUTE_SONGS_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Songs.txt";
    public static final String ROUTE_MODEL_STORIFY = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Storify.xml";


    public static void cargarDatosArchivos(Storify storify) throws IOException {
        // Cargar datos de usuarios
        HashMap<String, User> usuariosCargados = cargarUsuarios();
        if (!usuariosCargados.isEmpty()) {
            // Agregar usuarios al sistema de la casa de subasta
            storify.getUsersMap().putAll(usuariosCargados);
        }

        // Cargar datos de artistas
//        BinaryTree<Artist> artistasCargados = cargarArtistas();
//        if (artistasCargados != null && artistasCargados.size() > 0) {
//            // Agregar artistas al sistema de la casa de subasta
//            storify.setArbolArtistas(artistasCargados);
//        }

        // Puedes agregar la carga de más datos de otras clases (Anuncios, Compras, Productos) aquí
        // según sea necesario
    }
    //-------------------------------------USERS-------------------------------------------

    //Funcion para guardar los ususarios en el archivo de texto
    public static void saveUsers(HashMap<String, User> users) throws IOException {
        StringBuilder content = new StringBuilder();

        for (User user : users.values()) {
            content.append(user.getUserName()).append("@@");
            content.append(user.getPassword()).append("@@");
            content.append(user.getEmail()).append("@@");
            content.append(user.getSongList()).append("\n");
        }
        UtilFile.guardarArchivo(ROUTE_USER_FILE, content.toString(), false);
    }

    //Función para cargar los usuarios desde el archivo

    public static HashMap<String, User> cargarUsuarios() throws IOException {
        HashMap<String, User> usuarios = new HashMap<>();
        List<String> contenido = UtilFile.leerArchivo(ROUTE_USER_FILE);

        // Iterar sobre cada línea del contenido
        for (String linea : contenido) {
            // Dividir la línea en partes usando "@@" como delimitador
            String[] partes = linea.split("@@");

            if (partes.length >= 3) {
                // Crear un nuevo Usuario y establecer sus atributos
                User usuario = new User();
                usuario.setUserName(partes[0]);  // userName
                usuario.setPassword(partes[1]);  // password
                usuario.setEmail(partes[2]);  // email
                // Agregar el usuario al HashMap usando userName como clave
                usuarios.put(usuario.getUserName(), usuario);
            }
        }
        return usuarios;
    }


    //---------------------------SONG----------------------
    //--------------------------Artist---------------------
    //Metodo para guardar los artistas en el archivo txt
    public static void saveArtist(BinaryTree<Artist> arbolArtistas) throws IOException {
        StringBuilder contenido = new StringBuilder();

        // Obtener una lista de artistas en orden del BinaryTree
        List<Artist> listaArtistas = arbolArtistas.inOrderTraversal();

        // Iterar sobre cada artista en la lista
        for (Artist artista : listaArtistas) {
            // Agregar la información del artista al contenido
            contenido.append(artista.getCode()).append("@@")
                    .append(artista.getName()).append("@@")
                    .append(artista.getNationality()).append("@@")
                    .append(artista.getIsAlone()).append("\n");
                    //.append(artista.getSongList()).append("\n");
        }

        // Guardar el contenido en el archivo usando la función guardarArchivo
        UtilFile.guardarArchivo(ROUTE_ARTIST_FILE, contenido.toString(), false);
    }
    //Metodo que carga los artistas desde el archivo
    public static BinaryTree<Artist> loadArtist() throws IOException {
        BinaryTree<Artist> arbolArtistas = new BinaryTree<>();
        List<String> contenido = UtilFile.leerArchivo(ROUTE_ARTIST_FILE);

        // Iterar sobre cada línea del contenido
        for (String linea : contenido) {
            // Dividir la línea en partes usando "@@" como delimitador
            String[] partes = linea.split("@@");

            if (partes.length >= 4) {
                // Crear un nuevo objeto Artist y establecer sus atributos
                Artist artista = new Artist();
                artista.setCode(partes[0]);  // code
                artista.setName(partes[1]);  // name
                artista.setNationality(partes[2]);  // nationality
                artista.setAlone(Boolean.parseBoolean(partes[3]));  // isAlone

                // Agregar el artista al BinaryTree
                arbolArtistas.insertar(artista);
            }
        }

        return arbolArtistas;
    }

    //----------------------------------STORIFY XML-------------------------------------
    public static void saveResourceStorifyXML(Storify storify) {
        try {
            UtilFile.salvarRecursoSerializadoXML(ROUTE_MODEL_STORIFY, storify);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Storify loadResourceStorifyXML() throws IOException {

        Storify storify= null;

        try {
            storify = (Storify) UtilFile.cargarRecursoSerializadoXML(ROUTE_MODEL_STORIFY);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return storify;
    }





}

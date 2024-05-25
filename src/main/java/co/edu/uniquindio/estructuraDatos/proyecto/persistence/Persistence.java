package co.edu.uniquindio.estructuraDatos.proyecto.persistence;

import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.BinaryTree;
import co.edu.uniquindio.estructuraDatos.proyecto.DataStructure.DoublyLinkedList;
import co.edu.uniquindio.estructuraDatos.proyecto.controllers.ModelFactoryController;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Artist;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Enum.Gender;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;
import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.util.UtilFile;
import javafx.scene.image.Image;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Persistence {
    public static final String ROUTE_USER_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Users.txt";
    public static final String ROUTE_USER_FILE2 = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Users2.txt";
    public static final String ROUTE_ARTIST_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Artist.txt";
    public static final String ROUTE_ARTIST_FILE2 = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Artists2.txt";
    public static final String ROUTE_SONGS_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Songs.txt";
    public static final String ROUTE_SONGS_FILE2 = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Songs2.txt";
    public static final String ROUTE_MODEL_STORIFY = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Storify.xml";


    public static void cargarDatosArchivos(Storify storify) throws IOException {
        // Cargar datos de usuarios
        HashMap<String, User> usuariosCargados = loadUsers2();
//        for (Map.Entry<String, User> entry : usuariosCargados.entrySet()) {
//            User user = entry.getValue();
//            System.out.println(user.getAuxPersistence());
//            user.getSongListCircular().toCircular( user.getAuxPersistence() );
//        }
        if (!usuariosCargados.isEmpty()) {
            // Agregar usuarios al sistema de la casa de subasta
            storify.getUsersMap().putAll(usuariosCargados);
        }

         //Cargar datos de artistas
        BinaryTree<Artist> artistasCargados = loadArtistS2();
        if (artistasCargados != null /*&& artistasCargados.size() > 0*/) {
            // Agregar artistas al sistema de la casa de subasta
            storify.setArtistTree(artistasCargados);
        }
        //Cargar datos de canciones
        List<Song> listaCanciones= loadSongs2();
        if (listaCanciones != null) {
            storify.setSongList(listaCanciones);
        }

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
            content.append(user.getSongToList()).append("\n");
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

    public static void saveUsers2(HashMap<String, User> usersMap) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ROUTE_USER_FILE2))) {
            outputStream.writeObject(usersMap);
        } catch (IOException e) {
            System.err.println("Error al serializar la lista de usuarios: " + e.getMessage());
        }
    }

    public static HashMap<String, User> loadUsers2() {
        HashMap<String, User> aux= new HashMap<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ROUTE_USER_FILE2))) {
            aux = (HashMap<String, User>) inputStream.readObject();
            System.out.println(" Lista de users deserializada correctamente desde el archivo: " + ROUTE_USER_FILE2);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar la lista de users desde el archivo: " + e.getMessage());
        }
        return aux;
    }


    //---------------------------SONG----------------------
    public static void saveSongs(List<Song> canciones) throws IOException {
        StringBuilder contenido = new StringBuilder();

        // Iterar sobre cada canción en la lista
        for (Song cancion : canciones) {
            // Construir la línea con la información de la canción
            contenido.append(cancion.getCode()).append("@@")
                    .append(cancion.getName()).append("@@")
                    .append(cancion.getCover()).append("@@")  // Ajusta la representación según sea necesario
                    .append(cancion.getYear()).append("@@")
                    .append(cancion.getDuration()).append("@@")
                    .append(cancion.getGender()).append("@@")
                    .append(cancion.getLink()).append("@@")
                    //.append(cancion.getArtist().getName()).append("\n");  // Utiliza el código del artista
                    .append("Milo").append("\n");  // Utiliza el código del artista

        }
        // Guardar el contenido en el archivo usando la función guardarArchivo
        UtilFile.guardarArchivo(ROUTE_SONGS_FILE, contenido.toString(), false);
    }

    public static void saveSongs2(List<Song> songList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ROUTE_SONGS_FILE2))) {
            outputStream.writeObject(songList);
        } catch (IOException e) {
            System.err.println("Error al serializar la lista de canciones: " + e.getMessage());
        }
    }

    public static List<Song> loadSongs2() throws IOException {
        List<Song> aux = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ROUTE_SONGS_FILE2))) {
            aux = (List<Song>) inputStream.readObject();
            System.out.println(" Lista de canciones deserializada correctamente desde el archivo: " + ROUTE_SONGS_FILE2);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar la lista de canciones desde el archivo: " + e.getMessage());
        }
        return aux;
    }



    public static List<Song> loadSong() throws IOException {
        List<Song> songs = new ArrayList<>();
        List<String> content = UtilFile.leerArchivo(ROUTE_SONGS_FILE);

        // Iterar sobre cada línea del archivo
        for (String linea : content) {
            // Dividir la línea usando "@@" como delimitador
            String[] partes = linea.split("@@");

            if (partes.length >= 8) {
                // Crear un objeto Song
                Song cancion = new Song();
                cancion.setCode(partes[0]);  // code
                cancion.setName(partes[1]);  // name
                cancion.setCover((partes[2]));  // cover (debes adaptarlo para manejar la clase Image)
                cancion.setYear(partes[3]);  // year
                cancion.setDuration(partes[4]);  // duration
                cancion.setGender(Gender.valueOf(partes[5]));  // gender
                try {
                    cancion.setLink(new URL(partes[6]));  // link
                } catch (MalformedURLException e) {
                    System.err.println("URL no válida en la línea: " + linea);
                    continue;  // Salta esta canción si la URL no es válida
                }

                // Asumimos que hay un método cargarArtista() que devuelve un objeto Artist a partir de un código de artista
                Artist artista = ModelFactoryController.getStorify().getArtist(partes[7]);  // artist (cargar el artista desde un método específico)
                if (artista != null) {
                    cancion.setArtist(artista);
                }

                // Agregar la canción a la lista
                songs.add(cancion);
            }
        }

        return songs;
    }

    public static Image cargarImagen(String ruta) {
        // Cargar la imagen desde la ruta especificada
        Image imagen = new Image(ruta);
        return imagen;  // Devuelve el objeto Image
    }


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
                    .append(artista.getPhoto()).append("@@")
                    .append(artista.getIsAlone()).append("@@")
                    .append(artista.getSongList()).append("\n");
        }

        // Guardar el contenido en el archivo usando la función guardarArchivo
        UtilFile.guardarArchivo(ROUTE_ARTIST_FILE, contenido.toString(), false);
    }


    public static void saveArtist2(BinaryTree<Artist> arbolArtistas) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ROUTE_ARTIST_FILE2))) {
            outputStream.writeObject(arbolArtistas);
        } catch (IOException e) {
            System.err.println("Error al serializar la lista de artistas: " + e.getMessage());
        }
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
                artista.setPhoto(partes[3]);
                artista.setAlone(Boolean.parseBoolean(partes[4]));  // isAlone
                artista.setSongList(new DoublyLinkedList<>());
                // Agregar el artista al BinaryTree
                arbolArtistas.insertar(artista);
            }
        }

        return arbolArtistas;
    }


    public static BinaryTree<Artist> loadArtistS2() throws IOException {
        BinaryTree<Artist> aux = new BinaryTree<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ROUTE_ARTIST_FILE2))) {
            aux = (BinaryTree<Artist>) inputStream.readObject();
            System.out.println(" Lista de artistas deserializada correctamente desde el archivo: " + ROUTE_ARTIST_FILE2);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar la lista de artistas desde el archivo: " + e.getMessage());
        }
        return aux;
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


    public static Storify loadResourceStorifyXML() {

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

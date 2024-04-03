package co.edu.uniquindio.estructuraDatos.proyecto.persistence;

import co.edu.uniquindio.estructuraDatos.proyecto.model.Storify;
import co.edu.uniquindio.estructuraDatos.proyecto.model.User;
import co.edu.uniquindio.estructuraDatos.proyecto.utilities.FileUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Persistence {

    public static final String ROUTE_USERS_FILE = "src/main/resources/co/edu/uniquindio/estructuraDatos/proyecto/persistenceFiles/Users.txt";


    public static void loadDataFiles(Storify storify) throws FileNotFoundException, IOException{
        HashMap<String, User> loadedUsers = loadUsers();

        if(!loadedUsers.isEmpty()){
            storify.getUsersMap().putAll( loadedUsers );
        }

    }

    private static HashMap<String, User> loadUsers() throws FileNotFoundException,IOException {
        HashMap<String,User> users = new HashMap<>();
        ArrayList<String> content = FileUtil.leerArchivo(ROUTE_USERS_FILE);
        String line = "";
        for (int i = 0; i < content.size(); i++) {
            line = content.get(i);
            User user = new User();
            user.setUserName(line.split("@@")[0]);
            user.setPassword(line.split("@@")[1]);
            user.setEmail(line.split("@@")[2]);
        }
        return users;
    }

    public static void saveUsers(HashMap<String,User> userMap) throws IOException {
        String content = "";
        for (Map.Entry<String,User> entry : userMap.entrySet()){
            User user  = entry.getValue();
            content += user.getUserName() + "@@" + user.getPassword() +
                    "@@" + user.getEmail() + "\n";
        }
        FileUtil.guardarArchivo(ROUTE_USERS_FILE,content,false);
    }

}

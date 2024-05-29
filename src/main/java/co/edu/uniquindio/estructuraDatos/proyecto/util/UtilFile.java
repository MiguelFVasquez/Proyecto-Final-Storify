package co.edu.uniquindio.estructuraDatos.proyecto.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class UtilFile {

    //-------------------------FILES------------------------------//

    /**
     * Metodo que permite guardar los datos
     * @param ruta
     * @param contenido
     * @param flagAnexarContenido
     * @throws IOException
     * @throws IOException
     */
    public static void guardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) throws IOException, IOException {
        FileWriter fw = new FileWriter(ruta,flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    /**
     * Metodo que permite leer los datos guardados
     * @param ruta
     * @return
     * @throws IOException
     */
    public static ArrayList<String> leerArchivo(String ruta) throws IOException {

        ArrayList<String>  contenido = new ArrayList<String>();
        FileReader fr=new FileReader(ruta);
        BufferedReader bfr=new BufferedReader(fr);
        String linea="";
        while((linea = bfr.readLine())!=null)
        {
            contenido.add(linea);
        }
        bfr.close();
        fr.close();
        return contenido;
    }

    //--------------------------XML-------------------------------------------//

    /**
     * Metodo que permite cargar los recursos de un objeto serializado
     * @param rutaArchivo
     * @return
     * @throws IOException
     */
    public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

        XMLDecoder decodificadorXML;
        Object objetoXML;

        decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
        objetoXML = decodificadorXML.readObject();
        decodificadorXML.close();
        return objetoXML;

    }

    /**
     * Metodo de respaldo para los recursos serializados
     * @param rutaArchivo
     * @param objeto
     * @throws IOException
     */
    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {
        XMLEncoder codificadorXML;

        codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXML.writeObject(objeto);
        codificadorXML.close();
    }


}

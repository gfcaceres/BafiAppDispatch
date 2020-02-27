package pe.com.nextel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cedenosa on 04/08/2015.
 */
public class LoadPropertiesSingleton {
    static private LoadPropertiesSingleton _instance = null;
    static public Properties props = new Properties();


    protected LoadPropertiesSingleton() throws IOException{
        InputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(new File(Constante.FILE_PROPERTIES)) ;
            props.load(fileInputStream);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }

    static public LoadPropertiesSingleton instance(){
        try{
            if (_instance == null) {
                System.out.println("[LoadPropertiesSingleton] Se crea una nueva instancia");
                synchronized (LoadPropertiesSingleton.class) {
                    if (_instance == null) {
                        _instance = new LoadPropertiesSingleton();
                    }
                }
            }else{
                System.out.println("[LoadPropertiesSingleton] Se leen los valores ya cargados");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return _instance;
    }
}

package pe.com.nextel.servlet;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.displaytag.properties.TableProperties;


public class DisplayTagInitServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=default";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Inicializando Servlet");
        InputStream propStream = config.getServletContext().getResourceAsStream("WEB-INF/displaytag.properties");
        if(propStream==null)
            System.out.println("No se cargo stream de Properties");
        System.out.println("Cargando Properties");
        Properties props = new Properties();

        try {
            props.load(propStream);
            propStream.close();
            System.out.println("Cargo Stream");
        } catch (IOException e) {
            System.out.println("No se pudo cargar Properties");
        }
        TableProperties.setUserProperties(props);
        
        System.out.println("Se termino de cargar properties");
    }  
}

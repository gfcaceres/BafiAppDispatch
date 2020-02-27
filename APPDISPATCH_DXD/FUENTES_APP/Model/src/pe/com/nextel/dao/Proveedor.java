package pe.com.nextel.dao;


import java.io.FileInputStream;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import pe.com.nextel.util.Constante;
import pe.com.nextel.util.GenericObject;
import pe.com.nextel.util.LoadPropertiesSingleton;


public class Proveedor extends GenericObject{
    
    public Proveedor() 
    {
    }


    /**
     * @return
     */
    public static Connection getConnection() {
        Context ctx = null;
        Hashtable ht = new Hashtable();
        Connection connection = null;
        try {
        LoadPropertiesSingleton singleton = LoadPropertiesSingleton.instance();
        Properties properties = singleton.props;
        ht.put(Context.INITIAL_CONTEXT_FACTORY, properties.getProperty(Constante.INITIAL_CONTEXT_FACTORY));
        ht.put(Context.PROVIDER_URL,properties.getProperty(Constante.PROVIDER_URL)); 

            ctx = new InitialContext(ht);
            javax.sql.DataSource ds =
                (javax.sql.DataSource)ctx.lookup(properties.getProperty(Constante.JNDI_DATA_SOURCE));
            connection = ds.getConnection();
              } catch (Exception e) {
                   System.out.println("Error getting pooled connection from Factory Pooling:" + e);
                  
                   try {
                      if (connection != null){
                        connection.close();                  
                      }
                   } catch (SQLException ex) {
                      System.out.println("Error closing connection :" + ex);
                   }
               }      
        return connection;
    } 
        
}
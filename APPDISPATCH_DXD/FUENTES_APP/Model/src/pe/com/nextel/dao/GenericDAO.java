package pe.com.nextel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Arrays;

import oracle.jdbc.OracleCallableStatement;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.util.GenericObject;


/**
 * Motivo: Clase Gen�rica, del cual deben heredar todos los dem�s DAOs.
 * Contiene la definici�n del Log4J para todas las clases que hereden de �sta.
 * Se definen como Abstracta para que no se creen instancias de ella.
 * <br>Realizado por: <a href="mailto:richard.delosreyes@nextel.com.pe">Richard De los Reyes</a>
 * <br>Fecha: 26/08/2007
 * @see GenericObject
 */
public abstract class GenericDAO {
    private final static String BEGIN = " BEGIN ";
    private final static String END = " END ";
    private final static String COLON = ",";
    private final static String SEMICOLON = ";";
    private final static String BRACKET_LEFT = "(";
    private final static String BRACKET_RIGHT = ")";
    private final static String PARAMETER = "?";

    // Definici�n del Log4J para todas las clases que hereden de GenericDAO.
    protected static Logger logger = Logger.getLogger(GenericDAO.class);

    /**
     *
     * @see pe.com.nextel.util.GenericObject#formatException(Exception)
     */
    protected static String formatException(Exception e) {
        return GenericObject.formatException(e);
    }

    protected static String generateCallForPackage(String strPackageName,
        int numParametros) {
        StringBuffer sbInvokePackage = new StringBuffer(BEGIN);
        sbInvokePackage.append(strPackageName).append(BRACKET_LEFT);

        String[] strParameters = new String[numParametros];
        Arrays.fill(strParameters, PARAMETER);
        sbInvokePackage.append(StringUtils.join(strParameters, COLON));
        sbInvokePackage.append(BRACKET_RIGHT).append(SEMICOLON);
        sbInvokePackage.append(END).append(SEMICOLON);

        return sbInvokePackage.toString();
    }

    /**
     * Motivo:        Finaliza la conexi�n actual a la Base de Datos.
    * <br>Realizado por: <a href="mailto:richard.delosreyes@nextel.com.pe">Richard De los Reyes</a>
    * <br>Fecha: 13/10/2007.
     * @param conn - Conexi�n actual
     */
    protected static void closeObjectsDatabase(Connection conn,
        OracleCallableStatement cstmt, ResultSet rs) throws SQLException {
        try{
            close(rs, cstmt, null, null, conn);
        }catch(Throwable t){
                rs = null;
                cstmt = null;
                conn = null;
        }finally{
                rs = null;
                cstmt = null;
                conn = null;
        }
    }

    /**
           * Motivo:        Finaliza la conexi�n actual a la Base de Datos.
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - Conexi�n actual
           */
    protected static void closeObjectsDatabase(Connection conn,
        CallableStatement cstmt, ResultSet rs) throws SQLException {
        try{
            close(rs, null, cstmt, null, conn);
        }catch(Throwable t){
                rs = null;
                cstmt = null;
                conn = null;
        }finally{
                rs = null;
                cstmt = null;
                conn = null;
        }
    }

    /**
           * Motivo:        Cierra todo tipo de enlace con la conexi�n
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - Conexi�n actual
           */
    public static void close(ResultSet resultSet,
        OracleCallableStatement oracleStatement,
        CallableStatement callableStatement, Statement statement,
        Connection connection) {
        try {
            /**Asegura que se cierre el ResultSet**/
            if (resultSet != null) {
                close(resultSet);
            }

            /**Asegura que se cierre el OracleCallableStatement**/
            if (oracleStatement != null) {
                close(oracleStatement);
            }

            /**Asegura que se cierre el CallableStatement**/
            if (callableStatement != null) {
                close(callableStatement);
            }

            /**Asegura que se cierre el Statement**/
            if (statement != null) {
                close(statement);
            }

            /**Asegura que se cierre el Connection**/
            if (connection != null) {
                close(connection);
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexi�n del GenericDAO");
            e.printStackTrace();
        }catch(Throwable t){
                resultSet = null;
                statement = null;
                connection = null;
        }finally{
                resultSet = null;
                statement = null;
                connection = null;
        }
    }

    /**
           * Motivo:        Cierra la referencia del objeto ResultSet
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - ResultSet resultSet
           */
    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Vendor: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException();
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Problema de cierre al close(ResultSet)");
            e.printStackTrace();
        }
    }

    /**
           * Motivo:        Cierra la referencia del objeto OracleCallableStatement
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - OracleCallableStatement oracleStatement
           */
    public static void close(OracleCallableStatement oracleStatement) {
        try {
            if (oracleStatement != null) {
                oracleStatement.close();
                oracleStatement = null;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Vendor: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException();
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(
                "Problema de cierre al close(OracleCallableStatement)");
            e.printStackTrace();
        }
    }

    /**
           * Motivo:        Cierra la referencia del objeto CallableStatement
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - CallableStatement callableStatement
           */
    public static void close(CallableStatement callableStatement) {
        try {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Vendor: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException();
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Problema de cierre al close(CallableStatement)");
            e.printStackTrace();
        }
    }

    /**
           * Motivo:        Cierra la referencia del objeto Statement
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - Statement statement
           */
    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState:" + ex.getSQLState());
                System.out.println("Message:" + ex.getMessage());
                System.out.println("Vendor:" + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException();
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Problema de cierre al close(Statement)");
            e.printStackTrace();
        }
    }

    /**
           * Motivo:        Cierra la referencia del objeto Connection
       * <br>Realizado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
       * <br>Fecha: 26/09/2008
           * @param conn - Connection connection
           */
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Vendor: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException();
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Problema de cierre al close(Connection)");
            e.printStackTrace();
        }
    }
}

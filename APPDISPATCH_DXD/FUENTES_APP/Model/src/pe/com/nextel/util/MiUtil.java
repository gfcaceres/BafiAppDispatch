package pe.com.nextel.util;


//import org.apache.commons.lang.CharUtils;
import java.io.FileInputStream;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.TableBean;


public class MiUtil {

 private static final Logger logger = Logger.getLogger(MiUtil.class);
    /***********************************************************************
    ***********************************************************************
    ***********************************************************************
    *  INTEGRACION DE ORDENES Y RETAIL - INICIO
    *  REALIZADO POR: Carmen Gremios Cornelio
    *  FECHA: 28/08/2007
    ***********************************************************************
    ***********************************************************************
    ***********************************************************************/
    public static int parseInt(String val) {
        try {
            if ((val != null) && !val.equals("")) {
                int i = Integer.parseInt(val);

                return i;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error convirtiendo:" + val + " a int ERROR=" +
                e);
        }

        return 0;
    }

    public static double parseDouble(String val) {
        try {
            if ((val != null) && !val.equals("")) {
                double i = Double.parseDouble(val);

                return i;
            } else {
                double d = 0;

                return d;
            }
        } catch (Exception e) {
            System.out.println("ERROR convirtiendo:" + val +
                " a double ERROR=" + e);
        }

        int k = 0;

        return (double) k;
    }

    public static long parseLong(String val) {
        try {
            if ((val != null) && !val.equals("")) {
                val = extractComasPuntos(val);

                long i = Long.parseLong(val);

                return i;
            } else {
                long d = 0;

                return d;
            }
        } catch (Exception e) {
            System.out.println("ERROR convirtiendo:" + val + " a long ERROR=" +
                e);
        }

        int k = 0;

        return (long) k;
    }

    public static String extractComasPuntos(String cad) {
        String result = "";

        if (cad == null) {
            return null;
        }

        for (int j = 0; j < cad.length(); j++) {
            if (cad.charAt(j) == '.') {
                return result;
            }

            if (cad.charAt(j) != ',') {
                result = result + cad.charAt(j);
            }
        }

        return result;
    }

    public static boolean isNotNull(String val) {
        return (val != null) && !val.equals("");
    }

    public static String buildCombo(ArrayList objList, String strValor,
        String strTexto) {
        StringBuffer strbfCadena = new StringBuffer();
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;

        if (objList != null) {
            strbfCadena.append("<option value=\"\"></option>");

            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);

                if ((strDatoV != null) && !"".equals(strDatoV)) {
                    strbfCadena.append("<option value=\"" + strDatoV + "\">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildInboxCombo(ArrayList objList, String strValor,
        String strTexto, String strStatus) {
        StringBuffer strbfCadena = new StringBuffer();
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;

        if (objList != null) {
            strbfCadena.append("<option value=\"\"></option>");

            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);

                if ((strDatoV != null) && !"".equals(strDatoV)) {
                    if (!(strDatoV.equalsIgnoreCase(strStatus))) {
                        strbfCadena.append("<option value=\"" + strDatoV +
                            "\">");
                        strbfCadena.append(strDatoT);
                        strbfCadena.append("</option>\n");
                    }
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildComboExcluded(ArrayList objList, String strValor,
        String strTexto, String strValorExcluded) {
        StringBuffer strbfCadena = new StringBuffer();
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;

        if (objList != null) {
            strbfCadena.append("<option value=\"\"></option>");

            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);

                if ((strDatoV != null) && !"".equals(strDatoV) &&
                        !MiUtil.getString(strValorExcluded).equals(strDatoV)) {
                    strbfCadena.append("<option value=\"" + strDatoV + "\">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildList(ArrayList objList, String strValor,
        String strTexto) {
        StringBuffer strbfCadena = new StringBuffer();
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);

                if ((strDatoV != null) && !"".equals(strDatoV)) {
                    strbfCadena.append("<option value=\"" + strDatoV + "\">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildComboSelected(ArrayList objList, String strValor,
        String strTexto, String strValueSelected) {
        StringBuffer strbfCadena = new StringBuffer();
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;
        String strSelected = "";

        if (objList != null) {
            strbfCadena.append("<option value=\"\"></option>");

            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);
                strSelected = "";

                if ((strDatoV != null) && !"".equals(strDatoV)) {
                    if (strValueSelected.equals(strDatoV)) {
                        strSelected = "selected";
                    }

                    strbfCadena.append("<option value=\"" + strDatoV + "\"" +
                        strSelected + ">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildComboWithOneOption(String strValor,
        String strTexto) {
        StringBuffer strbfCadena = new StringBuffer();
        String strSelected = "selected";
        strbfCadena.append("<option value=\"0\"></option>");
        strbfCadena.append("<option value=\"" + strValor + "\"" + strSelected +
            ">" + strTexto + "</option>");

        return strbfCadena.toString();
    }

    public static String buildCombo(ArrayList objList, String strValor,
        String strTexto, String strTexto2) {
        StringBuffer strbfCadena = new StringBuffer();
        String strDatoV = null;
        String strDatoT = null;
        String strDatoT2 = null;
        HashMap h = null;

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);
                strDatoT2 = (String) h.get(strTexto2);

                if ((strDatoV != null) && !"".equals(strDatoV)) {
                    strbfCadena.append("<option value=\"" + strDatoV + "\">");
                    strbfCadena.append(strDatoT + " - " + strDatoT2);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String toFecha(java.sql.Date dateFecha) {
        try {
            if (dateFecha == null) {
                return "";
            }

            DateFormat formatter = null;
            formatter = new SimpleDateFormat("dd/MM/yyyy");

            String strFecha = formatter.format(dateFecha);

            return strFecha;
        } catch (Exception e) {
            System.out.println("Error getdate:" + e);
        }

        String s = null;

        return s;
    }

    public static String toFecha(java.sql.Timestamp dateFecha) {
        try {
            if (dateFecha == null) {
                return "";
            }

            DateFormat formatter = null;
            formatter = new SimpleDateFormat("dd/MM/yyyy");

            String strFecha = formatter.format(dateFecha);

            return strFecha;
        } catch (Exception e) {
            System.out.println("Error getdate:" + e);
        }

        String s = null;

        return s;
    }

    public static java.sql.Date toFecha(String strFecha, String inFormat) {
        java.sql.Date dtfecha = null;

        try {
            if ((strFecha == null) || "".equals(strFecha)) {
                return null;
            }

            //"yyyy-MM-dd HH:mm" 
            SimpleDateFormat input = new SimpleDateFormat(inFormat);
            dtfecha = new java.sql.Date(input.parse(strFecha).getTime());

            return dtfecha;
        } catch (Exception e) {
            System.out.println("Error toFecha:" + e);

            return null;
        }
    }

    public static String toFechaHora(java.sql.Timestamp tsFechaHora) {
        try {
            if (tsFechaHora == null) {
                return "";
            }

            DateFormat formatter = null;
            formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            String strFecha = formatter.format(tsFechaHora);

            return strFecha;
        } catch (Exception e) {
            System.out.println("Error toStringFechaHora:" + e);
        }

        return null;
    }

    public static Timestamp toFechaHora(String strFechaHora, String strFormato) {
        try {
            if (strFechaHora == null) {
                return null;
            }

            strFechaHora = strFechaHora.trim();

            SimpleDateFormat sdf = new SimpleDateFormat(strFormato);
            Timestamp t = new Timestamp(sdf.parse(strFechaHora).getTime());

            return t;
        } catch (Exception e) {
            System.out.println("Error toFechaHora:" + e);
        }

        return null;
    }

    public static String getString(String val) {
        if ((val != null) && !val.equals("")) {
            return val.trim();
        } else {
            return "";
        }
    }

    public static String initCap(String val) {
        String strInit = null;
        String strEnd = null;
        String strResult = null;

        if ((val != null) && !val.equals("")) {
            val = val.trim();
            strInit = val.substring(0, 1);
            strEnd = val.substring(1, val.length());
            strInit = strInit.toUpperCase();
            strEnd = strEnd.toLowerCase();
            strResult = strInit.concat(strEnd);

            return strResult;
        } else {
            return "";
        }
    }

    public static String getStringNull(String val) {
        if (val.equals("")) {
            return null;
        } else if ((val != null) && !val.equals("")) {
            return val.trim();
        } else {
            return val;
        }
    }

    public static String getIfNotEmpty(String val) {
        if ((val != null) && !val.equals("")) {
            return val.trim();
        } else {
            return "0";
        }
    }

    public static String getString(long val) {
        if (val == 0) {
            return "";
        } else {
            return String.valueOf(val);
        }
    }

    public static String getDate(String formato) {
        try {
            DateFormat formatter = null;
            formatter = new SimpleDateFormat(formato);

            String dato = formatter.format(new java.util.Date());

            return dato;
        } catch (Exception e) {
            System.out.println("Error getDate:" + e);
        }

        return "";
    }

    //Correcion del método estaba devolviendo como fecha 1969-12-31   
    public static java.sql.Date getDatePlSql() {
        try {
            //java.sql.Date dtfecha=new java.sql.Date(0);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date dtfecha = new java.sql.Date(utilDate.getTime());
            System.out.println("fecha" + dtfecha.toString());

            return dtfecha;
        } catch (Exception e) {
            System.out.println("Error getDate:" + e);
        }

        return null;
    }

    /*    public static String dateFormat(String fecha,String inFormat,String outFormat){
           try{
               //"yyyy-MM-dd HH:mm:ss z"
               SimpleDateFormat input = new SimpleDateFormat(inFormat);
               SimpleDateFormat output = new SimpleDateFormat(outFormat);
               java.sql.Date df = new  java.sql.Date(input.parse(fecha).getTime());
               String dato = output.format(df);
               return dato;
           }
           catch(Exception e){
               System.out.println("Error dateFormat:" + e);
               return null;
           }

        }

        public static String format(java.sql.Date dateFecha){
           try{
               DateFormat formatter = null;
               formatter = new SimpleDateFormat("dd/MM/yyyy");
               String strFecha = formatter.format(dateFecha);
               return strFecha;
           }
           catch(Exception e){
               System.out.println("Error getdate:" + e);
           }
           String s = null;
           return s;
        }

        public static String addDate(int num,String formato){ //yyyy:año, MM:mes, dd:dia
           try{
               DateFormat formatter = null;
               formatter=new SimpleDateFormat(formato);
               Calendar c1 = Calendar.getInstance();
               c1.setTime(new java.util.Date());
               c1.add(Calendar.DATE, num);
               String dato=formatter.format(c1.getTime());
                return dato;
           }
           catch(Exception e){
               System.out.println("Error getdate:" + e);
               return null;
           }

        }*/
    public static java.sql.Date getDateBD(String formato) { //dd/MM/yyyy

        java.sql.Date result = null;

        try {
            /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            java.sql.Date effect_from = new java.sql.Date(formatter.parse(txt1).getTime());
            java.sql.Date end_on = new java.sql.Date(formatter.parse(txt2).getTime());

            */
            SimpleDateFormat formatter = new SimpleDateFormat(formato);
            java.util.Date parsedDate = formatter.parse(getDate(formato));
            result = new java.sql.Date(parsedDate.getTime());

            return result;
        } catch (Exception e) {
            System.out.println("Error getDateBD:" + e);
        }

        return result;
    }

    public static java.sql.Timestamp getTimeStampBD(String formato) { //dd/MM/yyyy

        java.sql.Timestamp result = null;

        try {
            /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            java.sql.Date effect_from = new java.sql.Date(formatter.parse(txt1).getTime());
            java.sql.Date end_on = new java.sql.Date(formatter.parse(txt2).getTime());

            */
            SimpleDateFormat formatter = new SimpleDateFormat(formato);
            java.util.Date parsedDate = formatter.parse(getDate(formato));
            result = new java.sql.Timestamp(parsedDate.getTime());

            return result;
        } catch (Exception e) {
            System.out.println("Error getDateBD:" + e);
        }

        return result;
    }

    public static String formatDecimal(double x) {
        return (decimalFormat(x, "###,###,###.00", ',', '.'));
    }

    public static String decimalFormat(double x, String fmt, char sepMiles,
        char sepDecimales) {
        Locale locale = new Locale("en", "US");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(locale);
        simbolos.setGroupingSeparator(sepMiles);
        simbolos.setDecimalSeparator(sepDecimales);

        return new DecimalFormat(fmt, simbolos).format(x);
    }

    public static String getMessageClean(String msg) {
        String strMsgError = "";

        /*//strMsgError=strMsgError.replace(ascii[13],"");
        //strMsgError=strMsgError.replaceAll(CHR(13),"\n");
        if (strMsgError==null)
          return "Error provocado por un NullPointer";
        strMsgError=strMsgError.replace('"',' ');
        strMsgError=strMsgError.replaceAll("\\u000a"," ");//Línea nueva
        strMsgError=strMsgError.replaceAll("\\u000a"," ");//Línea nueva
        strMsgError=strMsgError.replaceAll("\\u0027","");//Comilla simple
        strMsgError=strMsgError.replaceAll("''","");

        return strMsgError;*/
        StringBuffer sbMsg = new StringBuffer("");
        int posIni = 0;
        int posFinal = 0;

        if (msg != null) {
            if (msg.length() > 0) {
                posFinal = msg.indexOf('\n'); // CAMBIO DE LINEA

                while (posFinal != -1) {
                    sbMsg.append(msg.substring(posIni, posFinal) + "\\n");
                    posIni = posFinal + 1;
                    posFinal = msg.indexOf('\n', posIni); // CAMBIO DE LINEA
                }

                if (posIni == 0) {
                    sbMsg.append(msg);
                } else {
                    sbMsg.append(msg.substring(posIni));
                }
            }
        }

        strMsgError = sbMsg.toString().replaceAll("''", "");
        strMsgError = strMsgError.replace('"', '\'');
        strMsgError = strMsgError.replaceAll("\\u000a", " "); //Línea nueva
        strMsgError = strMsgError.replaceAll("\\u000d", "");
        strMsgError = strMsgError.replaceAll("\\u0027", "");

        /*
        for(int i =0; i<strMsgError.length(); i++){
          System.out.println("Variable : " + strMsgError.charAt(i) + CharUtils.unicodeEscaped(strMsgError.charAt(i)));
        }*/
        return strMsgError;
    }

    public static String getStrClean(String strParam) {
        strParam = strParam.replaceAll("'", "");

        return strParam;
    }

    public static String decode(String strParam1, String strParam2,
        String strTrueValue, String strFalseValue) {
        strParam1 = ((strParam1 == null) ? "1" : strParam1);
        strParam2 = ((strParam2 == null) ? "1" : strParam2);

        if (strParam1.equals(strParam2)) {
            return strTrueValue;
        } else {
            return strFalseValue;
        }
    }

    public static String getDescripcion(ArrayList objList, String strValor,
        String strTexto, String strValue) {
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = (String) h.get(strTexto);

                if ((strValue != null) && !"".equals(strDatoV)) {
                    if (strValue.equals(strDatoV)) {
                        return strDatoT;
                    }
                }
            }
        }

        //System.out.println("strbfCadena-->"+strbfCadena);
        return "";
    }

    public static String getValue(ArrayList objList, int iIndice,
        String strFieldName) {
        HashMap objTabla = null;
        String strReturn = null;
        strFieldName = getString(strFieldName);

        try {
            objTabla = (HashMap) objList.get(iIndice);
            strReturn = (String) objTabla.get(strFieldName);
            strReturn = getString(strReturn);

            return strReturn;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getValueId(ArrayList objList, String strValor,
        String strTexto, String strDescripcion) {
        HashMap h = null;
        String strDatoV = null;
        String strDatoT = null;

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDatoV = (String) h.get(strValor);
                strDatoT = MiUtil.getString((String) h.get(strTexto));

                if ((strDescripcion != null) && !"".equals(strDatoT)) {
                    if (strDescripcion.equals(strDatoT)) {
                        return strDatoV;
                    }
                }
            }
        }

        return "";
    }

    public static String toAnswer(String strDato) {
        if (strDato == null) {
            return "";
        }

        if (Constante.ANSWER_YES.equals(strDato)) {
            return "Si";
        } else {
            return "No";
        }
    }

    public static String getDateIfNull(java.sql.Timestamp tsFechaHora) {
        String strFecha = null;

        if (tsFechaHora == null) {
            strFecha = getDate("dd/MM/yyyy HH:mm");
        } else {
            strFecha = toFechaHora(tsFechaHora);
        }

        return strFecha;
    }

    public static boolean contentInArrayList(ArrayList objList,
        String strNameFieldToCompare, String strValueToCompare) {
        HashMap h = null;
        String strDato = null;

        if (strValueToCompare == null) {
            return false;
        }

        strValueToCompare = strValueToCompare.toUpperCase();

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                h = (HashMap) objList.get(i);
                strDato = MiUtil.getString((String) h.get(strNameFieldToCompare))
                                .toUpperCase();

                if (strValueToCompare.equals(strDato)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean contentStringInArrayList(ArrayList objList,
        String strValueToCompare) {
        String strItem = null;
        String strDato = null;

        if (strValueToCompare == null) {
            return false;
        }

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                strItem = (String) objList.get(i);

                if (strValueToCompare.equals(strItem)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean contentInArray(int value, int[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            if (value == arreglo[i]) {
                return true;
            }
        }

        return false;
    }

    public static String escape(String strCadena) {
        String strReturn = "";

        if (strCadena != null) {
            strReturn = strCadena.replaceAll("\\u0027", "\'");
        }

        return strReturn;
    }

    public static String escape2(String strCadena) {
        String strReturn = "";

        if (strCadena != null) {
            char cComilla = '\u0022';
            strCadena = strCadena.replaceAll("'", "u01X270z");
            strCadena = strCadena.replaceAll("&", "u01X260z");
            strReturn = strCadena.replaceAll(cComilla + "", "u01X220z");
        }

        return strReturn;
    }

    public static String unescape2(String strCadena) {
        String strReturn = "";

        if (strCadena != null) {
            char cComilla = '\u0022';
            strCadena = strCadena.replaceAll("u01X270z", "'"); //comilla simple
            strCadena = strCadena.replaceAll("u01X260z", "&"); //ampersand                        
            strReturn = strCadena.replaceAll("u01X220z", cComilla + "");
        }

        return strReturn;
    }

    /***********************************************************************
    ***********************************************************************
    ***********************************************************************
    *  INTEGRACION DE ORDENES Y RETAIL - FIN
    *  REALIZADO POR: Carmen Gremios Cornelio
    *  FECHA: 28/08/2007
    ***********************************************************************
    ***********************************************************************
    ***********************************************************************/
    /***********************************************************************
    ***********************************************************************
    ***********************************************************************
    *  INTEGRACION DE ORDENES Y RETAIL - INICIO
    *  REALIZADO POR: Lee Rosales Crispin
    *  FECHA: 06/11/2007
    ***********************************************************************
    ***********************************************************************
    ***********************************************************************/
    public static void printBottomReplaceMessage(String appContext,
        String strMessage, PrintWriter out) {
        //out.println("<script>");
        out.println("alert('" + strMessage + "')");

        //out.println("</script>");
        out.println("location.replace('" + appContext +
            "/websales/Bottom.html');");
        out.close();
    }

    public static String getStringObject(Object[] val, int indice) {
        if (val != null) {
            return (String) val[indice];
        } else {
            return "";
        }
    }

    /***********************************************************************
    ***********************************************************************
    ***********************************************************************
    *  INTEGRACION DE ORDENES Y RETAIL - INICIO
    *  REALIZADO POR: Lee Rosales Crispin
    *  FECHA: 06/11/2007
    ***********************************************************************
    ***********************************************************************
    ***********************************************************************/
    /**
    * Motivo: Obtiene una fecha según determinado formato (Ejm: dd/MM/yyyy)
    * <br>Realizado por: <a href="mailto:richard.delosreyes@nextel.com.pe">Richard De los Reyes</a>
    * <br>Fecha: 24/10/2007
    *
    * @return Fecha como Cadena.
    * @param formato
    * @param fecha
    */
    public static String getDate(java.util.Date fecha, String formato) {
        try {
            return new SimpleDateFormat(formato).format(fecha);
        } catch (Exception e) {
            //GenericObject.logger.error(GenericObject.formatException(e));
        }

        return "";
    }

    /* Inicio Descomentar para Desarrollo*/
    
    public static Context getInitialContext() throws NamingException {       
       Hashtable env = new Hashtable();
             
       try {
        LoadPropertiesSingleton singleton = LoadPropertiesSingleton.instance();
        Properties properties = singleton.props;   
        env.put(Context.INITIAL_CONTEXT_FACTORY, properties.getProperty(Constante.INITIAL_CONTEXT_FACTORY));
        env.put(Context.PROVIDER_URL,properties.getProperty(Constante.PROVIDER_URL)); 
        env.put(Context.SECURITY_PRINCIPAL, properties.getProperty(Constante.SECURITY_PRINCIPAL));
        env.put(Context.SECURITY_CREDENTIALS, properties.getProperty(Constante.SECURITY_CREDENTIALS));          
        }catch(Exception e){
            e.printStackTrace();
        }       
       return new InitialContext(env);      
    }

   
    /*Fin Descomentar para Desarrollo */

    //  Inicio Descomentar para Local
    /*public static Context getInitialContext() throws NamingException {
        System.out.println("[Inicio][getInitialContext]");

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.evermind.server.rmi.RMIInitialContextFactory");
        env.put(Context.SECURITY_PRINCIPAL, "oc4jadmin");
        env.put(Context.SECURITY_CREDENTIALS, "welcome");
        env.put(Context.PROVIDER_URL,
            "ormi://localhost:23891/current-workspace-app");
        System.out.println("[Fin][getInitialContext]");

        return new InitialContext(env);
    }

    public static Context getInitialContextServ1() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.evermind.server.rmi.RMIInitialContextFactory");
        env.put(Context.SECURITY_PRINCIPAL, "admin");
        env.put(Context.SECURITY_CREDENTIALS, "welcome");
        env.put(Context.PROVIDER_URL,
            "ormi://localhost:23891/current-workspace-app");

        return new InitialContext(env);
    }

    public static Context getInitialContextServ2() throws NamingException {
        System.out.println("[Inicio][getInitialContextServ2]");

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.evermind.server.rmi.RMIInitialContextFactory");
        env.put(Context.SECURITY_PRINCIPAL, "admin");
        env.put(Context.SECURITY_CREDENTIALS, "welcome");
        env.put(Context.PROVIDER_URL,
            "ormi://172.25.103.56:23891/current-workspace-app");

        //env.put("dedicated.rmicontext", "true");   
        System.out.println("[Fin][getInitialContextServ2]");

        return new InitialContext(env);
    }
*/
    //Fin Descomentar para Local

    /***********************************************************************
     ***********************************************************************
     ***********************************************************************
     * INTEGRACION DE ORDENES Y RETAIL - INICIO
     * REALIZADO POR: Richard De los Reyes
     * FECHA: 19/11/2007
     ***********************************************************************
     ***********************************************************************
     **********************************************************************/
    public static BigDecimal defaultBigDecimal(Object objBigDecimal,
        BigDecimal bdDefault) {
        return (objBigDecimal == null) ? bdDefault : (BigDecimal) objBigDecimal;
    }

    public static String defaultString(Object objString, String strDefault) {
        return (objString == null) ? strDefault : objString.toString();
    }

    public static String buildComboSelected(ArrayList arrList,
        String strValueSelected) {
        StringBuffer strbfCadena = new StringBuffer();
        DominioBean dominio = new DominioBean();
        String strDatoV = null;
        String strDatoT = null;
        String strSelected = "";

        if (arrList != null) {
            strbfCadena.append("<option value=\"\"></option>");

            for (int i = 0; i < arrList.size(); i++) {
                dominio = (DominioBean) arrList.get(i);
                strDatoV = dominio.getValor();
                strDatoT = dominio.getDescripcion();
                strSelected = "";

                if (StringUtils.isNotEmpty(strDatoV)) {
                    if ((strDatoV != null) &&
                            strValueSelected.equals(strDatoV)) {
                        strSelected = "selected=\"selected\"";
                    }

                    strbfCadena.append("<option value=\"" + strDatoV + "\"" +
                        strSelected + ">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildComboSelected1(ArrayList arrList,
        String strValueSelected) {
        StringBuffer strbfCadena = new StringBuffer();
        DominioBean dominio = new DominioBean();
        String strDatoV = null;
        String strDatoT = null;
        String strSelected = "";

        if (arrList != null) {
            strbfCadena.append("<option value=\"\"></option>");

            for (int i = 0; i < arrList.size(); i++) {
                dominio = (DominioBean) arrList.get(i);
                strDatoV = dominio.getValor();
                strDatoT = dominio.getDescripcion();
                strSelected = "";

                if (StringUtils.isNotEmpty(strDatoV)) {
                    if ((strDatoV != null) &&
                            strValueSelected.equals(strDatoV)) {
                        strSelected = "selected=\"selected\"";
                    }

                    strbfCadena.append("<option value=\"" + strDatoT + "\"" +
                        strSelected + ">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    public static String buildComboSelected(ArrayList arrList,
        String strValueSelected, boolean bShowEmpty) {
        StringBuffer strbfCadena = new StringBuffer();
        DominioBean dominio = new DominioBean();
        String strDatoV = null;
        String strDatoT = null;
        String strSelected = "";

        if (arrList != null) {
            if (bShowEmpty) {
                strbfCadena.append("<option value=\"\"></option>");
            }

            for (int i = 0; i < arrList.size(); i++) {
                dominio = (DominioBean) arrList.get(i);
                strDatoV = dominio.getValor();
                strDatoT = dominio.getDescripcion();
                strSelected = "";

                if (StringUtils.isNotEmpty(strDatoV)) {
                    if ((strDatoV != null) &&
                            strValueSelected.equals(strDatoV)) {
                        strSelected = "selected=\"selected\"";
                    }

                    strbfCadena.append("<option value=\"" + strDatoV + "\"" +
                        strSelected + ">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }
        public static String buildComboSelected2(ArrayList arrList,
        String strValueSelected, boolean bShowEmpty) {
        StringBuffer strbfCadena = new StringBuffer();
        TableBean dominio = new TableBean();
        String strDatoV = null;
        String strDatoT = null;
        String strSelected = "";

        if (arrList != null) {
            if (bShowEmpty) {
                strbfCadena.append("<option value=\"\"></option>");
            }

            for (int i = 0; i < arrList.size(); i++) {
                dominio = (TableBean) arrList.get(i);
                strDatoV = dominio. getNpValue();
                strDatoT = dominio.getNpValueDesc();
                strSelected = "";

                if (StringUtils.isNotEmpty(strDatoV)) {
                    if ((strDatoV != null) &&
                            strValueSelected.equals(strDatoV)) {
                        strSelected = "selected=\"selected\"";
                    }

                    strbfCadena.append("<option value=\"" + strDatoV + "\"" +
                        strSelected + ">");
                    strbfCadena.append(strDatoT);
                    strbfCadena.append("</option>\n");
                }
            }
        }

        return strbfCadena.toString();
    }

    /***********************************************************************
         ***********************************************************************
         ***********************************************************************
         * INTEGRACION DE ORDENES Y RETAIL - FIN
         * REALIZADO POR: Richard De los Reyes
         * FECHA: 19/11/2007
         ***********************************************************************
         ***********************************************************************
         **********************************************************************/
    /***********************************************************************
    *  INICIO
    *  REALIZADO POR: Cristian Espinoza
    *  FECHA: 15/01/2008
    ***********************************************************************/
    public static String deleteTagHTML(String strCadena, int iTag) {
        String strCadenaResultante = "";

        for (int x = 0; x < strCadena.length(); x++) {
            if ((int) strCadena.charAt(x) != iTag) {
                strCadenaResultante += strCadena.charAt(x);
            }
        }

        return getStringNull(strCadenaResultante);
    }

    public static String getParameterCadenaURL(String parametros,
        String strParamEncontrar) {
        String strValorParam = "";
        int indInicio = parametros.indexOf(strParamEncontrar);

        if (indInicio != -1) { //el parametro existe en la URL enviada			

            int indFinValorParametro = parametros.indexOf("&", indInicio);

            if (indFinValorParametro == -1) { //es el ultimo elemento
                indFinValorParametro = parametros.length();
            }

            int indInicioValorParametro = parametros.indexOf("=", indInicio);
            strValorParam = parametros.substring(indInicioValorParametro + 1,
                    indFinValorParametro);
        }

        return strValorParam;
    }

    /***********************************************************************
    *  FIN
    *  REALIZADO POR: Cristian Espinoza
    *  FECHA: 15/01/2008
    ***********************************************************************/
    /**
     * @autor Odubock
     * 22/05/2008
     * @return retorna el mensaje extenso, formateado para poder mostrar sin errores la alerta o mensaje
     * @param message
     */
    public static String showCleanAlerts(String message) {
        StringBuffer sbMessage = new StringBuffer("");
        int posIni = 0;
        int posFinal = 0;

        if (message != null) {
            if (message.length() > 0) {
                posFinal = message.indexOf('\n');

                while (posFinal != -1) {
                    sbMessage.append(message.substring(posIni, posFinal) +
                        "\\n");
                    posIni = posFinal + 1;
                    posFinal = message.indexOf('\n', posIni);
                }

                if (posIni == 0) {
                    sbMessage.append(message);
                } else {
                    sbMessage.append(message.substring(posIni));
                }
            }
        }

        return sbMessage.toString().replace('"', '\'');
    }

    /**
     * Imprime el mensaje de error lanzado por una excepción.
     * @autor Estefanía Gamonal
     * 13/06/2008
     * @param message
     */
    public static void printErrorMessage(String strMessage, PrintWriter out) {
        out.println("<script>");
        out.println("alert('" + getMessageClean(strMessage) + "')");
        out.println("</script>");
        out.close();
    }

    /**
    * Imprime el tipo de teléfono para ST, Sí es 'S' devuelve 'Orginal', de lo contrario 'Refurbish' .
    * @autor Tomás Mogrovejo
    * 21/05/2009
    * @param message
    */
    public static String toTypePhone(String strDato) {
        if (strDato == null) {
            return "";
        }

        if (Constante.ANSWER_YES.equals(strDato)) {
            return "Original";
        } else {
            return "Refurbish";
        }
    }

    /**
    * Verifica si existe un string en un array de nptable bean.
    * @autor María Isabel Limaylla
    * 31/07/2009
    * @param objList message
    */
    public static boolean contentStringInArrayListOfNpTableBean(
        ArrayList objList, String strValueToCompare) {
        TableBean objTableBean = null;
        String strDato = null;

        if (strValueToCompare == null) {
            return false;
        }

        strValueToCompare = strValueToCompare.toUpperCase();

        if (objList != null) {
            for (int i = 0; i < objList.size(); i++) {
                objTableBean = new TableBean();
                objTableBean = (TableBean) objList.get(i);

                strDato = MiUtil.getString((String) objTableBean.getNpValue())
                                .toUpperCase();

                if (strValueToCompare.equals(strDato)) {
                    return true;
                }
            }
        }

        return false;
    }


    
    public static String trimNotNull(String cadena){
      if(cadena == null){
        cadena = "";
      }else{
        cadena = cadena.trim();
      }
      return cadena;
    }
    
    public static String repleaceCharacterSpecialByHTML(String cadena){
      cadena = trimNotNull(cadena);
      String vocales[] = {"á","Á","é","É","í","Í","ó","Ó","ú","Ú","ñ","Ñ"};
      String html[]    = {"&aacute;","&Aacute;","&eacute;","&Eacute;","&iacute;","&Iacute;","&oacute;","&iOcute;","&uacute;","&Uacute;","&nacute;","&Nacute;"};
      for(int i=0;i<vocales.length;i++){		
          cadena = cadena.replaceAll(vocales[i], html[i]);			
      }
		
        return cadena;
    }
    
    public static String repleaceCharacterSpecialByUNICODE(String cadena){
      cadena = trimNotNull(cadena);
      String vocales[] = {"á","Á","é","É","í","Í","ó","Ó","ú","Ú","ñ","Ñ"};
      String unicode[]    = {"\u00e1","\u00c1","\u00e9","\u00c9","\u00ed","\u00cd","\u00f3","\u00d3","\u00fa","\u00da","\u00f1","\u00d1"};
      for(int i=0;i<vocales.length;i++){		
          cadena = cadena.replaceAll(vocales[i], unicode[i]);			
      }		
        return cadena;
    } 
    
    public static String emptyValConcat(String val){
        return (val!=null && val.length()>0)?val:"";
    }
    
    public static void main(String edu[]){
      String cadena = "1111á 22222é  44444 Í";
      System.out.println("original: "+cadena);
      cadena = repleaceCharacterSpecialByUNICODE(cadena);
      System.out.println("cambiada: "+cadena);
    }
 
    
}

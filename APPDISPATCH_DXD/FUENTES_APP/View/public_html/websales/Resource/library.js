/*
	Validaciones con objetos
	- jsCheckAll
	- jsValidarCheckbox
	- jsValidarString
	- jsValidarText
	- jsValidarSelect
	- jsValidarInteger
	- jsValidarEmail
        - fxCounterTextarea(objTxt, objSpn, longMax)
	- fxValidarFloat(str,ndec)        

	Cadena
	- trim
	- ContentOnlyNumber
	- ContentOnlyNumberDec
	- CompletarNumeroDec
	- isValidDate
	- isValidHour
	- IsValidTime
	- leftPad(cadena,cantidad,caracter){
	- isValidHourBetweenRange(hourStr, liStr,lsStr,objeto)
	- compareDate(strStartDate , operador ,strEndDate)   "02/02/2003",">=","03/02/2004"    
        
	Mensajes
	- jsMensajeError

	COMBOS
	setSelected(valorPrincipal, ValorComparar)

	REDONDEO
	round_decimals(original_number, decimals)
	pad_with_zeros(rounded_value, decimal_places)

        PERMITE VALIDAR EL TELEFONO
        ValidPhoneWithCodeArea(nf_codearea ,nf_Phone,mensaje,bOblig)

*/ 

function fxTrim(s) {
		while (s.length>0 && (s.charAt(0)==" "||s.charCodeAt(0)==10||s.charCodeAt(0)==13)) {
			s=s.substring(1, s.length);
		}
		while (s.length>0 && (s.charAt(s.length-1)==" "||s.charCodeAt(s.length-1)==10||s.charCodeAt(s.length-1)==13)) {
			s=s.substring(0, s.length-1);
		}
		return s; 
	}

//#######################################################################################
 // Validaciones con objetos
 //#######################################################################################

	function jsCheckAll(form_name, checkbox_name, objeto_todos){
	/*
	Objetive: 	Marca o desmarcar todos los checkbox
	Input:		-form_name: Cadena con el nombre del formulario Ej. 'frmFormulario'
				-checkbox_name: Cadena con el nombre del checkbox. Ej. 'chkNumero'
				-objeto_todos: Checkbox que marca o desmarca los checkbox
				 Ej. frmFormulario.chkNumero
	*/

		var objForm = eval('document.'+form_name);
		var objCheckbox = eval('document.' + form_name + '.' + checkbox_name);
		var intLen = objForm.elements.length;
		var bolEstado;

		if (objeto_todos.checked) {
		     bolEstado = true;
		}else{
		     bolEstado = false;
		}

		for (var i = 0; i < intLen; i++) {
			var e = objForm.elements[i];
		    if (e.name == checkbox_name) {
			      e.checked = bolEstado;
		    }
		}

	}


	function jsValidarCheckbox(form_name, checkbox_name, etiqueta){
	/*
	Objetive: 	Validar que por lo menos se marque un checkbox
	Input:		-form_name: Cadena con el nombre del formulario Ej. 'frmFormulario'
				-checkbox_name: Cadena con el nombre del checkbox. Ej. 'chkNumero'
				-etiqueta: Cadena con el nombre de la etiqueta o label del checkbox
	Output:		-true: Si hay por lo menos un checkbox marcado
				-false: Si no hay ningún checkbox marcado. 
	*/

		var objForm = eval('document.'+form_name);
		var objCheckbox = eval('document.' + form_name + '.' + checkbox_name);
		var intLen = objForm.elements.length;
		var intCont = 0;
		var strAlertMessage;

		for (var i = 0; i < intLen; i++) {
			var e = objForm.elements[i];
			if (e.name == checkbox_name && e.checked) {
			  intCont++;
			}	
		}
			    
		if (intCont==0){
		    strAlertMessage = "Debe escoger por lo menos una opción del checkbox!";
		    jsMensajeError(etiqueta, strAlertMessage);
		    return (false);
		}else{
		    return (true);
		}
	       
	}

	function jsValidarString(objeto, etiqueta, min, max){
	/*
	Objetive: 	Validar la cadena dentro de un Textbox.
	Input:		-objeto: Nombre del objeto. Ej. frmFormulario.txtNombre
				-etiqueta: Cadena con el nombre de la etiqueta o label del Textbox.
				-min: Cantidad mínima de caracteres. Con el valor 0 se acepta que el input este vacío
				-max: Cantidad máxima de caracteres.
	Output:		-true
				-false
	*/
	        var strAlertMessage;
	        var bolResult= true;
	        var valTextoSimple = /^.*$/;
	        if (objeto.value.length < min)
	        {
	                strAlertMessage = "La caja de Texto \"" + etiqueta + "\" debe tener por lo menos " + min + " caracteres";
	                bolResult = false;
	        }else if (objeto.value.length > max) {
	                strAlertMessage = "La caja de Texto \"" + etiqueta + "\" debe tener a lo más " + max + " caracteres";
	                bolResult = false;
	        }else if (!valTextoSimple.test(objeto.value)) {
	                strAlertMessage = "La caja de Texto  \"" + etiqueta + "\" tiene caracteres inválidos.";
	                bolResult = false;
	        }

	        if (bolResult == false) {
	                //alert('Error en ['+ etiqueta + ']\n\n' + strAlertMessage);
	                jsMensajeError(etiqueta, strAlertMessage);
	                objeto.select();
	                objeto.focus()
	                return (false);
	        }

	        return (true);
	}

	function jsValidarText(objeto,etiqueta, min, max){
	/*
	Objetive: 	Validar la cadena dentro de un textarea.
	Input:		-objeto: Nombre del objeto. Ej. frmFormulario.txtNombre
				-etiqueta: Cadena con el nombre de la etiqueta o label del Textarea.
				-min: Cantidad mínima de caracteres. Con el valor 0 se acepta que el input este vacío
				-max: Cantidad máxima de caracteres.
	Output:		-true
				-false
	*/
	        var strAlertMessage;
	        var bolResult= true;
	        var valTextoSimple = /^.*$/;
	        if (objeto.value.length < min){
	                strAlertMessage = "La caja de Texto \"" + etiqueta + "\" debe tener por lo menos " + min + " caracteres";
	                bolResult = false;
	        }else if (objeto.value.length > max){
	                strAlertMessage = "La caja de Texto \"" + etiqueta + "\" debe tener a lo más " + max + " caracteres";
	                bolResult = false;
	        }


	        if (bolResult == false) {
		        	jsMensajeError(etiqueta, strAlertMessage);
	                //alert('Error en ['+ etiqueta + ']\n\n' + strAlertMessage);
	                objeto.select();
	                objeto.focus();

	                return (false);
	        }

	        return (true);
	}

	function jsValidarSelect(objeto,etiqueta){
	/*
	Objetive: 	Validar que se haya escogido una opción del objeto Select.
				Nota: Los <option></option> tiene que tener "value" es decir <option value=1>Uno</option>
	Input:		-objeto: Nombre del objeto. Ej. frmFormulario.cboTipo
				-etiqueta: Cadena con el nombre de la etiqueta o label Select (combo).
	Output:		-true
				-false
	*/
	        var strAlertMessage;
	        var bolResult = true;
	        //if (objeto[objeto.selectedIndex].value == 0)
	        if (objeto.value == ""){
	                strAlertMessage = "Debe escoger una opción del menú desplegable \"" + etiqueta + "\"";
	                bolResult= false;
	        }
	        if (bolResult == false) {
	                alert('Error en ['+ etiqueta + ']\n\n' + strAlertMessage);
	                //objeto.select(); Si se habilita da error
	                objeto.focus();
	                return (false);
	        }

	        return (true);
	}

	function jsValidarInteger(objeto, etiqueta, min, max){
	/*
	Objetive: 	Validar la cadena dentro de un textarea.
	Input:		-objeto: Nombre del objeto. Ej. frmFormulario.txtEdad.
				-etiqueta: Cadena con el nombre de la etiqueta o label.
				-min: Valor mínimo permitido.
				-max: Valor máximo permitido.
	Output:		-true
				-false
	*/
	        var strAlertMessage;
	        var bolResult= true;
	        var n;
	        n = objeto.value.length

	        RegExpDia = /^(-|)\d+$/
	        if (RegExpDia.test(objeto.value)){

	                var aux= parseInt(objeto.value);
	                if ((aux < min) || (aux > max)){
	                        strAlertMessage = "La caja de Texto \"" + etiqueta + "\" puede variar entre \"" + min + "\" y \"" + max + "\"";
	                        bolResult= false;
	                }
	        }else{
	                strAlertMessage = "En la caja de Texto \"" + etiqueta + "\" sólo se puede escribir números enteros.";
	                bolResult= false;
	        }

	        if (bolResult == false) {
		        	jsMensajeError(etiqueta, strAlertMessage);
	                //alert('Error en ['+ etiqueta + ']\n\n' + strAlertMessage);
	                objeto.select();
	                objeto.focus();
	                return (false);
	        }

	        return (true);
	}

	function jsValidarEmail(objeto, etiqueta) {
	/*
	Objetive: 	Validar el Email. Solo acepta un email.
	Input:		-objeto: Nombre del objeto. Ej. frmFormulario.txtEdad.
				-etiqueta: Cadena con el nombre de la etiqueta o label.
	Output:		-true
				-false
	*/
	        var bolResult;
	        var strAlertMessage;
	        var valEmail = /^[a-z_0-9][a-z_0-9\.]+@[a-z_0-9\.]+\.[a-z]+\.?[a-z]?$/i

	        bolResult = true;

	        if (!valEmail.test(objeto.value)) {
	                strAlertMessage = "El campo " + etiqueta + " está mal escrito o se encuentra vacío.";
	                bolResult = false;
	        }

	        if (bolResult == false) {
		        	jsMensajeError(etiqueta, strAlertMessage);
	                //alert('Error en ['+ etiqueta + ']\n\n' + strAlertMessage);
	                objeto.select();
	                objeto.focus();
	                return (false);
	        }

	        return (true);
	};


 //#######################################################################################
 // Cadenas
 //#######################################################################################


// Permite Eliminar los espacios en Blanco
function trim(str)
{
for(var i = 0 ; i<str.length && str.charAt(i)==" " ; i++ ) ;
cadena = str.substring(i,str.length); 
for(var i = cadena.length-1 ; i >=0 && cadena.charAt(i)==" " ; i-- ) ;
return cadena.substring(0,i+1); 
} 


function ContentOnlyNumber(str){
   ChrSpc = /\D/;
   var iposicion = str.search(ChrSpc);
   if(iposicion == -1){
      return true;
   }else{
      return false;
   }
}


function ContentOnlyNumberDec(str){
   ChrSpc = /^(\d*)(\.{0,1})(\d{0,2})$/;
   var matchArray = str.match(ChrSpc);
   if(matchArray == null){
      return false;
   }else{
      //entera= matchArray[1];
      //dec = matchArray[3];
      //str = entera+(dec+"00").substring(0,2);
      return true;
   }
}

function CompletarNumeroDec(str){
   ChrSpc = /^(\d*)(\.{0,1})(\d{0,2})$/;
   var matchArray = str.match(ChrSpc);
   if(matchArray == null){
      return str;
   }else{
      entera=matchArray[1];
      entera = (entera == "")?"0":entera;
      dec = matchArray[3];
      return entera+"."+(dec+"00").substring(0,2);
   }
}

function isValidDate(dateStr) {
   var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
   var matchArray = dateStr.match(datePat);
   if (matchArray == null) {
     alert(dateStr + " No es una fecha válida. Inténtelo nuevamente!")
     return false;
   }
   day = matchArray[1];
   month = matchArray[3];
   year = matchArray[4];
   if (month < 1 || month > 12) { 
     alert("El mes debe ser entre 1 y 12.");
     return false;
   }
   if (day < 1 || day > 31) {
     alert("Los días deben ser entre 1 y 31");
     return false;
   }
   if ((month==4 || month==6 || month==9 || month==11) && day==31) {
     var monthname = '';
     if ( month==4 ) monthname = 'Abril';
     if ( month==6 ) monthname = 'Junio';
     if ( month==9 ) monthname = 'Setiembre';
     if ( month==11 ) monthname = 'Noviembre';
     alert(""+monthname+" no tiene 31 dias!")
     return false;
   }
   if (month == 2) { 
     var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
     if (day>29 || (day==29 && !isleap)) {
       alert("Febrero " + year + " no tiene " + day + " días!");
       return false;
     }
   }
   return true;
}

function isValidHour(hourStr) {
   // Modificamos el patron de validación, de tal forma que no se 
   // tomen en cuenta los segundos.

   // var datePat = /^(\d{1,2})(:)(\d{1,2})(:)(\d{1,2})$/;
   var datePat = /^(\d{1,2})(:)(\d{1,2})$/;

   var matchArray = hourStr.match(datePat);
   if (matchArray == null) {
     alert(hourStr + " No es una hora válida. Inténtelo nuevamente!")
     return false;
   }
   
   hour = hourStr.substr(0,2);
   minute = hourStr.substr(3,2);
   // second = hourStr.substr(6,2);
   
   hour = hour * 1;
   minute = minute * 1;
   // second = second * 1;

   if (hour < 0 || hour > 23) { 
     alert("La hora debe estar entre 00 y 24.");
     return false;
   }
   if (minute < 0 || minute > 59) {
     alert("Los minutos deben estar entre 00 y 59");
     return false;
   }

   /*
   if (second < 0 || second > 59) {
     alert("Los segundos deben estar entre 00 y 59");
     return false;
   }
   */

   return true;
}


// Valida el Email
function isEmailAddress(theElement) {
   var s = theElement.value;
   var filter=/^[A-Za-z0-9_]+[.A-Za-z0-9_]*@[A-Za-z0-9_\-]+\.[A-Za-z0-9_.]+[A-za-z]$/;
   if (s.length == 0 ) return true;
   if (filter.test(s)){
      if (s.indexOf("..")==-1)
         return true;
      else
	alert("Debe ingresar un email válido, se encontró 2 puntos seguidos");
   }else 
      alert("Debe ingresar un email válido." );
   theElement.focus(); 
   theElement.select(); 
   return false;
}


function IsValidTime(timeStr)
{
   // Modificamos el patron de validación, de tal forma que no se 
   // tomen en cuenta los segundos.

   //var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
   var timePat = /^(\d{1,2}):(\d{2})$/;
   var matchArray = timeStr.match(timePat);
   if (matchArray == null)
   {
      alert("Formato de hora no válido.");
      return false;
   }
   hour = matchArray[1];
   minute = matchArray[2];
   //second = matchArray[4];
   //ampm = matchArray[6];

   //if (second=="") { second = null; }
   //if (ampm=="") { ampm = null }

   if (hour < 0  || hour > 23)
   {
      alert("La hora debe estar entre 0 y 23.");
      return false;
   }

   if (minute<0 || minute > 59)
   {
      alert ("Los minutos deben estar entre 0 y 59.");
      return false;
   }

   /*
   if (second != null && (second < 0 || second > 59))
   {
      alert ("Los segundos deben estar entre 0 y 59.");
      return false;
   }
   */

   return true;
}



function compararFechas(fechaInicio, fechaFin){
  var intFechaIni = 0;
  var intFechaFin = 0;
  var strFechaIni = fechaInicio.value;
  var strFechaFin = fechaFin.value;
  
  var posInicio = strFechaIni.indexOf('/');
	var dia = strFechaIni.slice(0,posInicio);
  strFechaIni = strFechaIni.substr(posInicio+1);
  var posInicio = strFechaIni.indexOf('/');
  var mes = strFechaIni.slice(0,posInicio);
  strFechaIni = strFechaIni.substr(posInicio+1);
  var anho = strFechaIni;
	strFechaIni = anho + mes + dia;  
  intFechaIni = parseInt(strFechaIni);
  
  posInicio = strFechaFin.indexOf('/');  
	dia = strFechaFin.slice(0,posInicio);
  strFechaFin = strFechaFin.substr(posInicio+1);
  posInicio = strFechaFin.indexOf('/');
  mes = strFechaFin.slice(0,posInicio);
  strFechaFin = strFechaFin.substr(posInicio+1);
  anho = strFechaFin;
	strFechaFin = anho + mes + dia;  
  intFechaFin = parseInt(strFechaFin);
  if(intFechaIni > intFechaFin){
    alert("La fecha de inicio no puede ser mayor a la fecha de fin");
    fechaInicio.focus();
    return false;
  }
  
  return true;
     
}

//*********************************** LOGICA DE NEGOCIO PARA CITAS *********************************************/
// FUNCION PARA VALIDAR QUE LA FECHA DE LA CITA
// SEA MAYOR A 30 DIAS ANTERIORES A LA FECHA ACTUAL
// Y MENOR A 90 DIAS POSTERIORES A LA FECHA ACTUAL
function validarTiempoFechaCita()
{
   //Almacena la Fecha Actual
	var gCurrentDate = new Date();

   //Almacena la Fecha de Cita
	var DateInicio = document.formdatos.v_fecha.value

   //Valida ;a Fecah de Cita, sea una fecha Válida
   if (isValidDate(DateInicio) == false)
   {
      return false;
   }

   //Coloca la Fecha en el formato (mm/dd/yyyy)
	var posInicio = DateInicio.indexOf('/');
	var dia = DateInicio.slice(0,posInicio);
   DateInicio = DateInicio.substr(posInicio+1);
   var posInicio = DateInicio.indexOf('/');
   var mes = DateInicio.slice(0,posInicio);
   DateInicio = DateInicio.substr(posInicio+1);
   var anho = DateInicio;
	DateInicio = mes +  "/" + dia + "/" + anho;

   //HALLA DIFERENCIA DE FECHAS, ENTRE LA FECHA DE CITA Y LA FECHA ACTUAL
   var Diferencia_fecha	= suycDateDiff(DateInicio, gCurrentDate, 'd',1);
   if (Diferencia_fecha > 30 )
   {
      alert("Fecha debera de ser Mayor a 1 mes de anterioridad");
      document.formdatos.v_fecha.focus();
      return false;
   }
   if (Diferencia_fecha < -90)
   {
      alert("Fecha debera de ser Menor a 3 mes siguientes");
      document.formdatos.v_fecha.focus();
      return false;
   }
   return true;
}

// FUNCION PARA HALLAR LA DIFERENCIA DEFECHA EN DIAS
function suycDateDiff( start, end, interval, rounding ) {

    var iOut = 0;

    // Create 2 error messages, 1 for each argument.
    var startMsg = "Verifique que la Fecha de Cita \n"
        startMsg += "tenga un formato de Fecha Válida.\n\n"
        startMsg += "Por favor pruebe otra vez." ;

    var bufferA = Date.parse( start ) ;
    var bufferB = Date.parse( end ) ;
    // check that the start parameter is a valid Date.
    if ( isNaN (bufferA) || isNaN (bufferB) ) {
        alert( startMsg ) ;
        //document.formdatos.v_fecha.focus();
    }

    var number = bufferB-bufferA ;

    // what kind of add to do?
    switch (interval.charAt(0))
    {
        case 'd': case 'D':
            iOut = parseInt(number / 86400000) ;
            if(rounding) iOut += parseInt((number % 86400000)/43200001) ;
            break ;
        case 'h': case 'H':
            iOut = parseInt(number / 3600000 ) ;
            if(rounding) iOut += parseInt((number % 3600000)/1800001) ;
            break ;
        case 'm': case 'M':
            iOut = parseInt(number / 60000 ) ;
            if(rounding) iOut += parseInt((number % 60000)/30001) ;
            break ;
        case 's': case 'S':
            iOut = parseInt(number / 1000 ) ;
            if(rounding) iOut += parseInt((number % 1000)/501) ;
            break ;
        default:
        // If we get to here then the interval parameter
        // didn't meet the d,h,m,s criteria.  Handle
        // the error.
        break ;
    }
    return iOut ;
}






function isHttpUrl( uri ) {
	var aux = '';
	var num = 0;
	var i = 0;
	var Estado = 0;
	while ( i < uri.length ) {
	   switch ( Estado ) {
	      case 0:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	         Estado = 1;
	               break;
	            case 'Numeric':
      	         Estado = 6;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 1:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
      	         Estado = 2;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 2:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
      	         Estado = 2;
	               break;
	            case 'Dot':
	               Estado = 3;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 3:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
      	         Estado = 4;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 4:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
      	         Estado = 5;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 5:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
      	         Estado = 5;
	               break;
	            case 'Dot':
	               Estado = 3;
	               break;
	            case 'Slash':
	               Estado = 13;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 6:
	         aux+= uri.substr(i-1,1);
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 6;
	               break;
	            case 'Dot':
	               Estado = 7;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 7:
	         num = aux*1;
	         if( num==0 || num==127 || num>255 ) {
	            return false;
	         }
	         aux = '';
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 8;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 8:
	         aux+= uri.substr(i-1,1);
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 8;
	               break;
	            case 'Dot':
	               Estado = 9;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 9:
	         num = aux*1;
	         if( num==0 || num==127 || num>255 ) {
	            return false;
	         }
	         aux = '';
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 10;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 10:
	         aux+= uri.substr(i-1,1);
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 10;
	               break;
	            case 'Dot':
	               Estado = 11;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 11:
	         num = aux*1;
	         if( num==0 || num==127 || num>255 ) {
	            return false;
	         }
	         aux = '';
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 12;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 12:
	         aux+= uri.substr(i-1,1);
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'Numeric':
      	         Estado = 12;
	               break;
	            case 'Slash':
      	         Estado = 13;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 13:
	         if( aux != '' ) {
	            num = aux*1;
	            if( num==0 || num==127 || num>255 ) {
	               return false;
	            }
	         }
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
      	         Estado = 14;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      case 14:
	         switch( getToken( uri.substr(i,1) ) ) {
      	      case 'AlphaNumeric':
      	      case 'Numeric':
	            case 'Dot':
      	         Estado = 14;
	               break;
	            case 'Slash':
      	         Estado = 13;
	               break;
      	      default:
	               return false;
	         }
	         break;
	      default:
	         return false;
	         break;
	   }
	   i++;
	}
	if( Estado==5 || Estado==12 || Estado==13 || Estado==14 )
	   return true;
	else
	   return false;
}

function getToken( str ){
   if ( isNaN(str) ) {
      if ( str == '.' ) {
         return 'Dot';
      } else if ( str == '/' ) {
         return 'Slash';
      } else if ( isSpecial(str) ){
         return 'Special';
      } else {
         return 'AlphaNumeric';
      }
   } else {
      return 'Numeric'
   }
}

function isSpecial( str ) {
   cte = '!"#$%&()=?¡¿\@|*+¨´[]{}^`,;:';
   if ( cte.indexOf(str) >= 0 ) {
      return true;
   }
   return false;
}

   function nuevaFecha(dd,mm,yyyy,dia){
	//alert("dd"+dd);
	//alert("mm"+mm);
	//alert("yyy"+yyyy);
	//alert("dia"+dia);
	var fecha = new Date();
	timeA = Date.UTC(yyyy,mm-1,dd);
	timeA = timeA+(dia*24*60*60*1000);
	fecha= new Date(timeA);
	return fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getYear();
   }

function urlEncode(str)
{
	var ms = "%25#23 20?3F<3C>3E{7B}7D[5B]5D|7C^5E~7E`60"
	var msi = 0
	var i,c,rs,ts
	str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g,"");
	while (msi < ms.length)
	{
		c = ms.charAt(msi)
		rs = ms.substring(++msi, msi +2)
		msi += 2
		i = 0
		while (true)
		{
			i = str.indexOf(c, i)
			if (i == -1) break
			ts = str.substring(0, i)
			str = ts+"%"+rs+str.substring(++i, str.length)
		}
	}
	return str
}


function trxTime(dateStr){
   var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
   var matchArray = dateStr.match(datePat);
   var result = "";

   day   = "00" + matchArray[1]
   day   = day.substring(day.length-2,5);

   month = "00" + matchArray[3];
   month = month.substring(month.length-2,5);

   year  = "0000"+matchArray[4];
   year  = year.substring(year.length-4,9);

   result = year+month+day;
   return result;
}

function trxMMDDYYYY(dateStr){
   var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
   var matchArray = dateStr.match(datePat);
   var result = "";

   day   = "00" + matchArray[1]
   day   = day.substring(day.length-2,5);

   month = "00" + matchArray[3];
   month = month.substring(month.length-2,5);

   year  = "0000"+matchArray[4];
   year  = year.substring(year.length-4,9);

   result = month+"/"+day+"/"+year;
   return result;
}

function URLPortal(){
   var url = location.href;
   pos = url.indexOf("&",url.indexOf("_schema"));
   if (pos!=-1){
      url = url.substring(0,pos);
   }
   return url;
}

function getParameter ( queryString, parameterName ) {
   // Add "=" to the parameter name (i.e. parameterName=value)
   var parameterName = parameterName + "=";
   if ( queryString.length > 0 ) {
      // Find the beginning of the string
      begin = queryString.indexOf ( parameterName );
      // If the parameter name is not found, skip it, otherwise return the value
      if ( begin != -1 ) {
         // Add the length (integer) to the beginning
         begin += parameterName.length;
         // Multiple parameters are separated by the "&" sign
         end = queryString.indexOf ( "&" , begin );
         if ( end == -1 ) {
            end = queryString.length
         }
         // Return the string
         return unescape ( queryString.substring ( begin, end ) );
      }
      return "";
   }
}

/* 	Gustavo Palomino 	15/11/2005	Creado */
function setParameter ( queryString, parameterName, parameterValue ) {
   // Returns queryString modified
   // No changes if the paramenter has not been found

   // Add "=" to the parameter name (i.e. parameterName=value)
   var parameterName = parameterName + "=";
   var newurl = "";

   if ( queryString.length > 0 ) {
      // Find the beginning of the string
      begin = queryString.indexOf ( parameterName );

      // If the parameter name is not found, skip it.
      if ( begin != -1 ) {
         // Add the length (integer) to the beginning
         begin += parameterName.length;
         // Multiple parameters are separated by the "&" sign
         end = queryString.indexOf ( "&" , begin );
         if ( end == -1 ) {
            end = queryString.length
         }

         // Return the modified string
         newurl = queryString.substring (0, begin)+escape(parameterValue) +queryString.substring ( end, queryString.length );
      }
   }

   if ( newurl==""){
      newurl = queryString;
   }
   return  newurl;
}
// Funcion que retorna el valor del RADIO elegido, en caso que no se encuentre algun radio elegido retorna vacio
function verifRadio(Elegido){
   if (Elegido[0]!=null){
      for (i=0;i<Elegido.length;i++){
         if (Elegido[i].checked==true){
            return Elegido[i].value;
         }
      }
      return "";
   }else{
      if (Elegido.checked){
         return Elegido.value;
      }else{
         return "";
      }
   }
}

// Permite reemplazar caracteres de una cadena
// Cualquier modificaion hacerlo tambien en : WEBSALES.NPSL_LIBRARY_PKG.FX_GET_CADENA
function fxGetCadena(av_cadena){
   var wv_cadena = '';

   wv_cadena = av_cadena;
   wv_cadena = wv_cadena.replace(/\ /gi,'_');
   wv_cadena = wv_cadena.replace(/\./gi,'_');
   wv_cadena = wv_cadena.replace(/\//gi,'_');
   wv_cadena = wv_cadena.replace(/\\/gi,'_');
   wv_cadena = wv_cadena.replace(/\&/gi,'_');
   wv_cadena = wv_cadena.replace(/\:/gi,'_');
   wv_cadena = wv_cadena.replace(/\?/gi,'_');
   wv_cadena = wv_cadena.replace(/\=/gi,'_');
      
   wv_cadena = wv_cadena.replace(/\á/gi,'a');
   wv_cadena = wv_cadena.replace(/\é/gi,'e');
   wv_cadena = wv_cadena.replace(/\í/gi,'i');
   wv_cadena = wv_cadena.replace(/\ó/gi,'o');
   wv_cadena = wv_cadena.replace(/\ú/gi,'u');
      
   return wv_cadena;
}

// Graba un valor al COOKIE.
// Cookie expires at the end of the user's browser session : SetCookie( Name, Value );

function SetCookie(name, value, expires, path, domain, secure) 
{ document.cookie = name + "=" + escape(value) + 
  ((expires == null) ? "" : "; expires=" + expires.toGMTString()) +
  ((path == null)    ? "" : "; path=" + path) +
  ((domain == null)  ? "" : "; domain=" + domain) +
  ((secure == null)  ? "" : "; secure");
}

// Recupera un valor del Cookie
function GetCookie(name)
{ var cname = name + "=";               
  var dc = document.cookie;             
  if (dc.length > 0) 
  { begin = dc.indexOf(cname);       
    if (begin != -1) 
    { begin += cname.length;       
      end = dc.indexOf(";", begin);
      if (end == -1) end = dc.length;
      return unescape(dc.substring(begin, end));
    } 
  }
  return "";
}

/////////////////////////////////////////////////
// compara dos fechas
// Si fecha1 < fecha2 devuelve numero > 0
// Si fecha1 > fecha2 devuelve numero < 0
////////////////////////////////////////////////

function TiempoFecha1ToFecha2(fecha1, fecha2 )
{
var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;

var matchArray1 = fecha1.match(datePat);

day1 = matchArray1[1];
month1 = matchArray1[3];
year1 = matchArray1[4];

var matchArray2 = fecha2.match(datePat);

day2 = matchArray2[1];
month2 = matchArray2[3];
year2 = matchArray2[4];

f1 = new Date(year1,month1-1,day1);
f2 = new Date(year2,month2-1,day2);

time = eval(f2 - f1);

return time;
}



/***************************** INICIO - FUNCION USADAS POR EL INBOX *************************/
function getInboxIncidentDetail(customerId,incidentid,inboxName,inboxid,actionId,workingFeature){
   location.href="/portal/page/portal/nextel/INCIDENT_DETAIL?customerid="+customerId+"&incidentid=" + incidentid +"&menu=mInbox&menu_sub="+escape(inboxName) +"&inboxName=" + escape2(inboxName) + "&inboxId=" + inboxid + "&actionId=" + actionId +"&workingFeature=" + workingFeature;
}

function getIncidentEditCC(incidentid){
   location.href="/portal/page/portal/nextel/INCIDENT_EDIT_CC?incidentid=" + incidentid;
}


function getInboxLife_CycleDetail(customerId,lifecycleId,inboxName){
            location.href='/portal/pls/portal/!WEBSALES.NPSAC_LIFE_CYCLE_PL_PKG.PL_LIFECYCLE_REDIRECT?lifeCycleId=' + lifecycleId +'&menu=mInbox&menu_sub='+escape2(inboxName);
}

// customerId no es usado, en caso se requiera se debe usar
function getInboxFfpedidosDetail(customerId,orderId,inboxName){
   //location.href='/portal/page/portal/nextel/ORDER_DETAIL?orderId=' + orderId +"&menu=mInbox&menu_sub="+escape2(inboxName);
   var url="/portal/pls/portal/ORDERS.PLI_ORDER_REDIRECT?an_nporderid="+orderId+"&av_execframe=BOTTOMFRAME";
   parent.bottomFrame.location.replace(url);
}


function getOrderDetailAC(orderId){
   //location.href='/portal/page/portal/nextel/AC_ORDER_EDIT?orderId=' + orderId;
   //var url="/portal/pls/portal/WEBSALES.NPSL_ORDER_PL_PKG.PL_TYPE_ORDER_DETAIL?an_orderid="+orderId;
   var url="/portal/pls/portal/ORDERS.PLI_ORDER_REDIRECT?an_nporderid="+orderId+"&av_execframe=BOTTOMFRAME";
   parent.bottomFrame.location.replace(url);
}

function getInboxReparacionesDetail(customerId,repairId,inboxName){
   location.href='/portal/page/portal/nextel/REPAIR_EDIT?txtCustomerId=' +customerId+ '&txtRepairId=' +repairId;
}               

function getInboxReparaciones_ExtDetail(customerId,repairExtId,inboxName){
  var pantalla="INBOX";
  location.href='/portal/page/portal/nextel/REPAIR_EXT_EDIT?txtCustomerId=' +customerId+ '&txtRepairExtId=' +repairExtId+ '&wv_pantalla=' + pantalla;
}


 /*********************************************************************************
   * Objetivo  : Del Inbox de instalaciones abre la página de edición de la instalación
   * Developer :Hugo Moreno
   * Fecha Modificación    : 15/10/2007		Se cambió el nombre de la pagína de edición
   **********************************************************************************/ 
function getInboxInstalacionDetail(customerId,InstalacionId,inboxName,inboxId){
   location.href='/portal/page/portal/ORDERS/ORDER_INSTALL_EDIT?txtCustomerId=' +customerId+ '&txtInstalacId=' +InstalacionId + '&txtInboxName='+inboxName + '&txtInboxId='+inboxId;
}
function getInboxPlanDetail(id_plan){
 var url = "/portal/pls/portal/WEB_PLANES.NPRP_INBOX_PL_PKG.PL_INBOX_WIN?an_codplan="+id_plan;
WinAsist = window.open(url,"WinAsist","toolbar=no,location=0,directories=no,status=yes,menubar=0,scrollbars=auto,resizable=yes,screenX=300,top=450,left=300,screenY=300,width=500,height=275,modal=yes");
return;
}

//function getInboxApproveDetail(customerId,pedidoid,inboxName,inboxid){
//   location.href="/portal/page/portal/nextel/APPROVE_EDIT?txtCustomerId=" +customerId+"&txtPedidoId=" + pedidoid +"&menu=mInbox&menu_sub="+escape(inboxName) +"&inboxName=" + escape2(inboxName) + "&inboxId=" + inboxid;
//}

function getInboxFfpedidos_ExceptionDetail(customerId,orderExceptionId,inboxName,inboxid){
   location.href="/portal/page/portal/nextel/APPROVE_EDIT?txtCustomerId="+customerId+"&orderExceptionId="+orderExceptionId+"&txtInboxName="+escape2(inboxName)+"&txtInboxId="+inboxid;
}

function getInboxCamdetDetail(customerId,campaigndetailId,inboxName,inboxid){
//alert("customerId "+customerId+ " campaigndetailId "+campaigndetailId+ " inboxName "+inboxName+" inboxid "+inboxid);
location.href="/portal/pls/portal/!CAMPAIGN_WEB.PLI_CAMPAIGN_DETAIL_REDIRECT?campaigndetailId="+campaigndetailId;
}





/***************************** FIN - FUNCION USADAS POR EL INBOX *************************/

function escape2(av_cadena){
    var wv_cadena = '';

   wv_cadena = av_cadena;
   wv_cadena = wv_cadena.replace(/\+/gi,'%2B');
   wv_cadena = wv_cadena.replace("\u2013","-");
   wv_cadena = escape(wv_cadena);
   return wv_cadena;
    
}

 //#######################################################################################
 // Mensajes
 //#######################################################################################
 
	
 	function jsMensajeError(etiqueta, mensaje){
	/*
	Objetive: 	Levantar una ventana de Alert con un formato predefinido
	Input:		-etiqueta: Cadena con el nombre de la etiqueta o label.
				-mensaje: Cadena con el mensaje.
	*/
		alert(mensaje);
	}


                  function setSelected(valorPrincipal, ValorComparar){
                     var resultado = "";
                     if (valorPrincipal == ValorComparar){
                        resultado = "SELECTED";
                     }
                     return resultado;
                  }


function round_decimals(original_number, decimals) {
    var result1 = original_number * Math.pow(10, decimals)
    var result2 = Math.round(result1)
    var result3 = result2 / Math.pow(10, decimals)
    return pad_with_zeros(result3, decimals)
}

function pad_with_zeros(rounded_value, decimal_places) {

    // Convert the number to a string
    var value_string = rounded_value.toString()
    
    // Locate the decimal point
    var decimal_location = value_string.indexOf(".")

    // Is there a decimal point?
    if (decimal_location == -1) {
        
        // If no, then all decimal places will be padded with 0s
        decimal_part_length = 0
        
        // If decimal_places is greater than zero, tack on a decimal point
        value_string += decimal_places > 0 ? "." : ""
    }
    else {

        // If yes, then only the extra decimal places will be padded with 0s
        decimal_part_length = value_string.length - decimal_location - 1
    }
    
    // Calculate the number of decimal places that need to be padded with 0s
    var pad_total = decimal_places - decimal_part_length
    
    if (pad_total > 0) {
        
        // Pad the string with 0s
        for (var counter = 1; counter <= pad_total; counter++) 
            value_string += "0"
        }
    return value_string
}


/************ INCIO DE VECTOR *************/
/* Objetivo   : Emular al Vector de Java
 PERSON		DATE		COMMENT
 --------------	----------	----------------------------------------------------
 JEVANGELISTA	18/12/2003	CREADO
*/
function addElementToVector(obj){
   this.objects[this.objects.length] = obj;
}

function setElementAt(obj,i){
   this.objects[i] = obj;    
}

function removeElementAtToVector(i){
   var newObjects = new Array();
   var idx = 0;
   var j;
   i = i-1;
   if (i<this.objects.length){
      for (j=0;j<this.objects.length;j++){
         if (j!=i){
            newObjects[idx] = this.objects[j];
            idx=idx+1;
         }
      }
      this.objects = newObjects;
   }
}

function removeElementAll(){
   
  this.objects = new Array();
   
}

function getVectorSize(){
   return this.objects.length;
}
           
function getElementFromVector(index){
   if (index<this.objects.length) {
      return this.objects[index];
   }else{
      return;
   }
}


function Vector(){
   this.objects         = new Array();
   this.addElement      = addElementToVector;
   this.removeElementAt = removeElementAtToVector;
   this.size            = getVectorSize;
   this.elementAt       = getElementFromVector;
   this.setElementAt    = setElementAt;
   this.removeElementAll= removeElementAll;   
}


/************ FIN DE VECTOR *************/


function leftPad(cadena,cantidad,caracter){
   longitud = cadena.length;
   for(i=longitud;i<cantidad;i++){
      cadena = caracter+cadena;
   }
   return cadena;
}

function isValidHourBetweenRange(hourField, liStr,lsStr,objeto) {
   hora = hourField.value;
   if ( hora == "" ) return true;

   var datePat = /^(\d{1,2})(:)(\d{1,2})$/;
   var matchArray = hora.match(datePat);
   if (matchArray == null) {
     alert(hourStr + " No es una hora válida. Inténtelo nuevamente!")
     return false;
   }

   hour    = leftPad(matchArray[1],2,"0");
   minute  = leftPad(matchArray[3],2,"0");
   nhour   = hour * 1;
   nminute = minute * 1;

   liHour   = leftPad(liStr.substr(0,2),2,"0");
   liMinute = leftPad(liStr.substr(3,2),2,"0");
   nliHour  = liHour * 1;
   nliMinute = liMinute * 1;

   lsHour    = leftPad(lsStr.substr(0,2),2,"0");
   lsMinute  = leftPad(lsStr.substr(3,2),2,"0");
   nlsHour   = lsHour * 1;
   nlsMinute = lsMinute * 1;


   if (nhour < 0 || nhour > 23) { 
     alert("La hora de "+objeto+" debe estar entre 00 y 24.");
     hourField.focus();
     hourField.select();
     return false;
   }

   if (nminute < 0 || nminute > 59) {
     alert("Los minutos de "+objeto+" deben estar entre 00 y 59");
     hourField.focus();
     hourField.select();
     return false;
   }


   if (hour+minute<liHour+liMinute || hour+minute>lsHour+lsMinute){
     alert("La hora de "+objeto+" debe estar entre las "+liStr+" y "+lsStr);
     hourField.focus();
     hourField.select();
     return false;
   }

   return true;
}

   /* PERMITE VALIDAR EL TELEFONO DANDO EL CODIGO DE AREA ,
      LIMA/CALLAO Celular inicia con    9 / son de 8 dígitos
      LIMA/CALLAO FIJO    NO inicia con 9 / son de 7 dígitos
      PROVINCIA   Celular Inicia con    9 / son de 7 dígitos
      PROVINCIA   FIJO    No inicia con 9 / son de 6 dígitos
   */

   function ValidPhoneWithCodeArea(nf_codearea ,nf_Phone,mensaje,bOblig){
      var matchArray;
      var regExp = "";
      var formato = "";
      var codearea = nf_codearea.value;
      var phone = nf_Phone.value;


      if (bOblig==true){
         if (codearea=="" && phone==""){
            alert("El "+mensaje+" es obligatorio");
            nf_codearea.focus();
            return false;
         }
         if (codearea==""){
            alert("Ingrese el código de área del "+mensaje);
            nf_codearea.focus();
            return false;
         }
         if (phone==""){
            alert("El "+mensaje+" no puede ser vacio");
            nf_Phone.focus();
            return false;
         }
      }else{
         if (codearea=="" && phone==""){
            return true;
         }
      }

      if (codearea==""){
         alert("Ingrese el código de área del "+mensaje);
         nf_codearea.focus();
         return false;
      }

      if (phone==""){
         alert("Ingrese el "+mensaje);
         nf_Phone.focus();
         return false;
      }

      /* Valida que sea el teléfono sólo dígitos */
      regExp = /^(\d*)$/;

      matchArray = phone.match(regExp);
      if(matchArray == null){
         alert("El "+mensaje+" debe ser sólo números");
         nf_Phone.focus();
         return false;
      }
      

      if (codearea=="1"){
         /* Lima/Callao */
         if (phone.substring(0,1)=="9"){
            /* celular */
            regExp = /^(\d{9})$/;
            formato = "Los Celulares tienen 9 dígitos\nEste Celular tiene "+phone.length+" dígitos";
         }else{
            /* fijo */
            regExp = /^(\d{7})$/;
            formato = "Los teléfonos Fijo tienen 7 dígitos\nEste Teléfono tiene "+phone.length+" dígitos";
         }         
      }else{
         /* PRovincia */
         if (phone.substring(0,1)=="9"){
            /* celular */
            regExp = /^(\d{9})$/;
            formato = "Los Celulares de Provincia tienen 9 dígitos\nEste Celular tiene "+phone.length+" dígitos";
         }else{
            /* fijo */
            regExp = /^(\d{6})$/;
            formato = "Los teléfonos Fijo de Provincia tienen 6 dígitos\nEste Teléfono tiene "+phone.length+" dígitos";
         }  
      }

      var matchArray = phone.match(regExp);
      if(matchArray == null){
         alert(formato);
         nf_Phone.focus();
         return false;
      }else{
         return true;
      }
   }


function fxFindQuotationMarks(object){
   if ((object.indexOf("\'") >= 0) || (object.indexOf("\"") >= 0)){
      return true;
   }
   else{
      return false;
   }
}

/***********************
*	DATE	       *
***********************/function y2k(number) { return (number < 1000) ? number + 1900 : number; }

function compareDate(strStartDate , operador ,strEndDate){
   var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
   var matchArray = strStartDate.match(datePat);
   var startDay = matchArray[1];
   var startMonth = matchArray[3];
   var startYear = matchArray[4];
   
   matchArray = strEndDate.match(datePat);
   var endDay = matchArray[1];
   var endMonth = matchArray[3];
   var endYear = matchArray[4];

   var startdate = new Date(startYear-0,startMonth-1,startDay-0);
   var enddate   = new Date(endYear-0,endMonth-1,endDay-0);
   
   starttime = Date.UTC(y2k(startdate.getYear()),startdate.getMonth(),startdate.getDate(),0,0,0);
   endtime = Date.UTC(y2k(enddate.getYear()),enddate.getMonth(),enddate.getDate(),0,0,0);
   
   if (eval("starttime "+operador+" endtime")){
        return true;
   }else {
      return false
   }
}





   function makeStatusChange(currentStatusId,nextStatusId,nextStatusDesc,changeAllowed, message,tag1,tag2) {
      this.currentStatusId   = currentStatusId;
      this.nextStatusId      = nextStatusId; 
      this.nextStatusDesc    = nextStatusDesc;
      this.changeAllowed     = changeAllowed;
      this.message   	     = message;
      this.tag1 	     = tag1;
      this.tag2 	     = tag2;
   }	

   function makeNPTable(npvalue,npvaluedesc,nptag1,nptag2) {

      this.npvalue      = npvalue;
      this.npvaluedesc  = npvaluedesc; 
      this.nptag1       = nptag1;
      this.nptag2       = nptag2;

   }


/******************************************************************
******* Funciones usadas para contar en un TextArea - Inicio ******
******************************************************************/
   // Creado por: Carola Ascona (10/08/2006)
   // fxCounterTextarea sirve para validar la longitud maxima de caracteres del control Textarea enviado como parámetro
   // Además actualiza el objeto span que indica la logitud actual del campo.
   function fxTextareaCounter(objTxt, objSpn, longMax){	
      var mensaje = objTxt.value;
      objSpn.innerText = mensaje.length;
      if(mensaje.length>longMax) 	{
         objTxt.focus();
         alert("La longitud máxima del contenido es de " + longMax + " caracteres. \nCualquier texto superior a esta longitud será recortado a " + longMax + " caracteres.");
         objTxt.value = trim(objTxt.value).substring(0,longMax);
         objSpn.innerText = longMax;
      }
   }
/******************************************************************
******* Funciones usadas para contar en un TextArea - Fin ******
******************************************************************/


// Funcion que extrae un parametro de una direccion  
// RPOLO	06/09/2006	Creacion	
function fxDeleteParameter(queryString, parameterName) {
   var str="";	
   parameterName = parameterName + "=";
  
   if ( queryString.length > 0 ) {	
	begin=queryString.indexOf( parameterName );
	if ( begin != -1 ) {
		end=queryString.indexOf( "&" , begin );
		if ( end == -1 ) {
			end = queryString.length;
	         }
		if ( queryString.indexOf( "?", begin -1) == begin-1){  // Por si es el primer parametro despues de ? 
			str=queryString.substring(0, begin) + queryString.substring(end+1, queryString.length);
		}
                else {  // por si es segundo a mas parametro
			str=queryString.substring(0, begin-1) + queryString.substring(end, queryString.length); 
		}
	}
        if ( str == "" ){
	   str=queryString;
	}
   }
   return str;
}


function doNothing(){
   window.status="";
}

   /*********************************************************************************
   * Objetivo  : Calcula el números de objetos con el valor de la propiedad ID similar.
   * Developer : José Evangelista & Estefanía Gamonal 
   * Fecha     : 04/01/2007
   **********************************************************************************/
   function fxCounterLikeId(nameId){
      var countId=0;
      for(i = 0; i < document.all.length; i++){
         if ((document.all(i).id).indexOf(nameId)>=0){
            countId++;
         }
      }

      return countId;
   }
   
   /*********************************************************************************
   * Objetivo  : valida si es numero decimal y con ndec decimales 
		 1 : Ok; 0 : no tiene ndec decimales;  -1: no es numero
   * Developer : Ruth Polo
   * Fecha     : 17/08/2007
   **********************************************************************************/
    function fxValidarFloat(str,ndec){
        ChrSpc = /^(\d*)(\.{0,1})(\d*)$/;
        var matchArray = str.match(ChrSpc);
        var ventera, vdec, vcant_dec=0;
 
        if (matchArray == null) {
            return -1;
        }
        else {
            ventera = matchArray[1];
            vdec = matchArray[3];
            vcant_dec = vdec.length;
        }
 
        if (vcant_dec >0){
            if (vcant_dec <= ndec){
                return 1;
            }
            else {
                return 0;
            }
        }
    }
    
    function getInboxControl_EquipmentDetail(v_customerid, v_ordenid, v_inbox, v_numero1, v_numnero2, v_codigo ){
       var v_url="/portal/pls/portal/!CONTROL_EQUIPMENT.NP_ACTIVATION_RENT_PL_PKG.PL_ACTIVATION_RENT_DECIDE?wv_npoarid="+v_ordenid;
       
       parent.bottomFrame.location.replace(v_url);
    }



 /*********************************************************************************
 * Objetivo  : Función para mostrar una catidad determinada de decimales de un numero sin redondear.
 * Developer : MVERAE
 * Fecha     : 08/11/2007
 **********************************************************************************/
function truncateNumber(minutos,numDec){
   pot = Math.pow(10,numDec);
   num = parseInt(minutos * pot) / pot;
   nume = num.toString().split('.');

   entero = nume[0];
   decima = nume[1];
   if (decima != undefined){
   	fin = numDec-decima.length; 
   }else {
   	decima = '';
   	fin = numDec; 
   }
   for(i=0;i<fin;i++)
   	decima+=String.fromCharCode(48); 

   num=entero+'.'+decima;
   return num;
}


 /*********************************************************************************
 * Objetivo  : Funcion que permite solo el ingreso de numeros
 * Developer : Cristian Espinoza 
 * Fecha     : 29/01/2008
 **********************************************************************************/   
	try{
    var vValor = window.event ? true : false;
  }catch(e){
    var vValor = window.Event ? true : false;	
  }
  

	function fxOnlyNumber(evt){ 
		// NOTA: 0 = 48, 9 = 57
		var vKey = vValor ? evt.which : evt.keyCode; 
		return (vKey <= 13 || (vKey >= 48 && vKey <= 57));
	} 

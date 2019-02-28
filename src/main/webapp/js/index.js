function $(selector){ //Para seleccionar el primer elemento selector
    return document.querySelector(selector);
}
/**
 * Para seleccionar todos los elementos selector
 * Si quieres escoger elementos por clase el parametro sera ".elemento"
 * Si quieres escoger elementos por id el parametro sera "#elemento"
 * Si quieres escoger todo el body, el parametro sera "body"
 * Si quieres escoger elementos por name en un input el parametro sera: input[name='nombre']"
 * @param {*} selector 
 */
function $all(selector){
    return document.querySelectorAll(selector);
}
/**
 * A continuacion funciones auxiliares
 */
function insertAsLastChild(padre,nuevoHijo){
    padre.appendChild(nuevoHijo);
}
function insertAsFirstChild(padre,nuevoHijo){
    padre.insertBefore(nuevoHijo,padre.children[0]);
}
function insertBeforeChild(padre,hijo,nuevoHijo){
    padre.insertBefore(nuevoHijo,hijo);
}
function removeElement(nodo){
    nodo.remove();
}
function analizaUrl(url){
	//var re = new RegExp("hola"+"[A-z]+"+"adios"+"[0-9]+"); //true devuelve holaAsdfadios1 el + da obligatoriedad
	var re = new RegExp("https://twitter.com/"+"[0-z]+"+"/status/"+"[0-9]+");
	return re.test(url);
}

function extraerTweet(){
	var url = $("input[name='buscador']").value;
	
	if(url == ""){
		alert("Es necesario especificar una URL.");
	}
	else{
		var valido = analizaUrl(url);
		
		if(valido){
			var i = url.lastIndexOf('/');
			var idTweet = "";
			//De la url introducida extraemos el id del tuit
			for(var j = i+1; j < url.length; j++){
				idTweet += url[j];
			}

			//Extraemos el texto del tuit
			fetch('extraetweet?id='+idTweet)
			.then(function(response){
				return response.json();
			})
			.then(function(responseAsJson){	
				if("error" in responseAsJson){
					alert("Ha ocurrido un error extrayendo el tuit: " + error);
				}
				else{
                    console.log(responseAsJson);
                    window.location.href=responseAsJson.UrlRedirecciona;
				}
			})
			.catch(function(error){
				alert("Ha ocurrido un error extrayendo el tuit: " + error);
			})
		}
		else{
			alert("Introduzca una URL de tweet válida, formato: https://twitter.com/\"usuario\"/status/\"código tuit\"");
		}
	}
}

/** 
 * Iniciar todos los elementos
*/
function init(){
	var verificar_btn= $(".main-button");
	verificar_btn.addEventListener("click",extraerTweet);
	
}

window.addEventListener("load", init);
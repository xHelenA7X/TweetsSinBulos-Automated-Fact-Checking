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

function dibujaTweetsComunidad(div,json){
    var divTweet = document.createElement("div");
    divTweet.setAttribute("id","tweet");
    divTweet.setAttribute("tweetID",json.idTweetRelacionado);
    insertAsLastChild(div,divTweet);

    var tweet = divTweet;
    var id = json.idTweetRelacionado;

    twttr.widgets.createTweet(
      id, tweet, 
      {
        conversation : 'none',    // or all
        cards        : 'hidden',  // or visible 
        linkColor    : '#cc0000', // default is blue
        theme        : 'light'    // or dark
      })
    .then (function (el) {
      //el.contentDocument.querySelector(".footer").style.display = "none";
    });
}

function conclusionExpertos(conclusion){
	var array = conclusion.split(" ");
	var posicion = array.indexOf("que");
	var cadena = "";
	
	for(var i = posicion+1; i < array.length; i++){
		cadena = cadena + array[i] + " ";
	}
	cadena = cadena.replace(".","");
	return cadena;
}

function JSON(){
    var idTweet = $("input[name='idTweet']").value;
    fetch('extraetweet?action=listaResultados&idTweet='+idTweet,{credentials: 'same-origin'})
	 .then(function(response){
		return response.json();
	})
	.then(function(responseAsJson){	
		var output;
		if("error" in responseAsJson) {
			console.log("error");
        }
        else{
        	console.log(responseAsJson);
            $(".lead").innerHTML = responseAsJson.texto;
            $(".conclusion").innerHTML = responseAsJson.conclusion;
            $(".e1").innerHTML = responseAsJson.salidaCorpus;
            var veracidad = responseAsJson.veracidad;
            if(veracidad == "1.0"){
            	$(".e2").innerHTML = "Lo que dice el autor del tweet es CIERTO.";	
            }
            else if(veracidad == "0.0"){
            	$(".e2").innerHTML = "Lo que dice el autor del tweet es FALSO.";	
            }
            var veracidadNoticia = responseAsJson.veracidadNoticia;
            var fin = "";
            var strConclusionExpertos = conclusionExpertos(responseAsJson.conclusion);
            
            if(veracidadNoticia == "True"){
				$(".e3").innerHTML = "Que " + strConclusionExpertos + "ha sido catalogada por expertos como NOTICIA VERDADERA";
				fin = "verdadera";
            }
            else if(veracidadNoticia == "Fake"){
            	$(".e3").innerHTML = "Que " + strConclusionExpertos + " ha sido catalogada por expertos como NOTICIA FALSA";
            	fin = "falsa";
            }
            $(".e4").innerHTML = "Título de la noticia "+fin+" en cuestión: " + responseAsJson.tituloNoticia;
            $(".e5").setAttribute("href", responseAsJson.linkNoticia);
            $(".e5").innerHTML = "Link: " + responseAsJson.linkNoticia;
            $(".e6").innerHTML = responseAsJson.cuerpoNoticia;
            var divTweets = $(".tweets-comunidad");
            for(var i = 0; i < responseAsJson.tweetsRelacionados.length; i++){ //Controlamos que no imprima el mismo tweet analizado
                if(responseAsJson.idTweet == responseAsJson.tweetsRelacionados[i] && i == responseAsJson.tweetsRelacionados.length-1){
                    break;
                }else if(responseAsJson.idTweet == responseAsJson.tweetsRelacionados[i]){
                    i++;
                }
                else{dibujaTweetsComunidad(divTweets,responseAsJson.tweetsRelacionados[i]);}
            }
            
        }
	})
	.catch(function(error){
		alert("Ha ocurrido un error listando los resultados: " + error);
	})	

}



/** 
 * Iniciar todos los elementos
*/
function init(){

    JSON();
	
}

window.addEventListener("load", init);
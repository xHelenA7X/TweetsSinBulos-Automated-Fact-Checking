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

function JSON(){
    fetch('extraetweet?action=listaResultados',{credentials: 'same-origin'})
	 .then(function(response){
		return response.json();
	})
	.then(function(responseAsJson){	
		var output;
		if("result" in responseAsJson) {
			console.log(responseAsJson.result);
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
	twttr.widgets.createTimeline(
    {
        sourceType: "collection",
        id: "539487832448843776",
    },
    document.getElementById("container"),
    {
        height: 400,
        chrome: "nofooter",
        linkColor: "#820bbb",
        borderColor: "#a80000"
    }
    );
    console.log("Hola mundo");
    JSON();
	
}

window.addEventListener("load", init);
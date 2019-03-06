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
		
		if(!valido){
		    alert("Introduzca una URL de tweet válida, formato: https://twitter.com/\"usuario\"/status/\"código tuit\"");
		}
	}
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
    console.log($("input[name='json']"));
    console.log($("input[name='json']").placeholder);
    var urls = $("input[name='json']").placeholder;
    console.log(urls.urlRelated[0]);
	
}

window.addEventListener("load", init);
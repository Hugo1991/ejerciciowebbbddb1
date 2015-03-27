function show(bloq){
    alert("Hola Mundo!");
	if(bloq == 'PRODUCTOS'){
	document.getElementById(bloq).style.display='block';
	document.getElementById('PEDIDOS').style.display='none';
	}
	if(bloq == 'PEDIDOS'){
	document.getElementById(bloq).style.display='block';
	document.getElementById('PRODUCTOS').style.display='none';
	
}
	
}
function mostrarEdicion(bloq,iden){
    alert("Hola Mundo!");
if(bloq == 'editar'){
	document.getElementById(iden).style.display='block';
}

if(bloq == 'salir-editar'){
	document.getElementById(iden).style.display='none';  
}
}
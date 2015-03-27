function show(bloq){
	if(bloq == 'PRODUCTOS'){
	document.getElementById(bloq).style.display='block';
	document.getElementById('PEDIDOS').style.display='none';
	}
	if(bloq == 'PEDIDOS'){
	document.getElementById(bloq).style.display='block';
	document.getElementById('PRODUCTOS').style.display='none';
	
}
	
}
function mostrarEdicion(bloq){
if(bloq == 'editar'){
	document.getElementById(bloq).style.display='block';
}

if(bloq == 'salir-editar'){
	document.getElementById('editar').style.display='none';  
}
}
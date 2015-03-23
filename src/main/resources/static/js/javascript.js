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


function Delete(r,i) {
	var j = r.parentNode.parentNode.rowIndex;
	document.getElementById("table-infor").deleteRow(j);
	var form = document.createElement("form");
	document.body.appendChild(form);
	form.method = "post";
	form.action = "AddToCartController";
	var input = document.createElement("input");
	input.name = "id-del";
	input.value = i;
	input.type = 'hidden';
	
	form.appendChild(input);
	form.submit();
	
	
}

function Calculation(id) {
	
	var i = (parseFloat(document.getElementById("quantity").value)).toFixed(2);
	var j = (parseFloat(document.getElementById("price").innerHTML)).toFixed(2);
	if(isNaN(i) || i < 0){
		return;
	}
	document.getElementById("total").innerHTML = (i*j).toFixed(2);
	
	var quantity = parseInt(document.getElementById("quantity").value);
	var form = document.createElement("form");
	document.body.appendChild(form);
	form.method = "post";
	form.action = "AddToCartController";	
	var input = document.createElement("input");
	input.name = "add";
	input.value = id+";"+quantity;
	input.type = 'hidden';
	
	
	form.appendChild(input);
	form.submit();
	
}



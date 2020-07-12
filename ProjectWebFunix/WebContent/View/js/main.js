function deleteRow(r) {
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("table-infor").deleteRow(i);
}
function Calculation() {
	var i = parseFloat(document.getElementById("inputChangeClass").value);
	var j = parseFloat(document.getElementById("price").innerHTML);
	document.getElementById("total-amount").innerHTML = i*j;
}
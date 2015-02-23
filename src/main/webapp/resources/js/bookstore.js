function addToCart(username,bookId) {
	if (username.length==0){
	  alert("please login first!");
	  return;
	}
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("result").innerHTML = xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET", "/addToCart/" + username + "/" +bookId, true);
	xmlhttp.send();
}
function deleteFromCart(username,bookId) {
	if (username.length==0){
	  alert("please login first!");
	  return;
	}
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("result").innerHTML = xmlhttp.responseText;
			window.location.reload(true);
		}
	}
	xmlhttp.open("GET", "/deleteFromCart/" + username + "/" +bookId, true);
	xmlhttp.send();
}
function placeNewOrder(username) {
	if (username.length==0){
	  alert("please login first!");
	  return;
	}
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if(xmlhttp.responseText=="Success"){
				window.location.href="/orders/"+username;
			}else{
				document.getElementById("result").innerHTML = xmlhttp.responseText;
			}
		}
	}
	xmlhttp.open("GET", "/placeNewOrder/" + username, true);
	xmlhttp.send();
}
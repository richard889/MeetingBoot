function isInptNum(val) {
	var keyCode = event.keyCode;
	var strCash = val + "" + event.key;
	if (keyCode == 46 || keyCode == 190 || keyCode == 8 || keyCode == 37 || keyCode == 39)
		return true;
	if (!/^[0-9]+(\.[0-9]{0,4})?$/.test(strCash))
		event.preventDefault ? event.preventDefault() : event.returnValue = false;
}

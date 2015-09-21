/*
 * Disable F5
 */
var version = navigator.appVersion;

function showKeyCode(e) {
	var keycode = (window.event) ? event.keyCode : e.keyCode;
	
	if ((version.indexOf('MSIE') != -1)) {
		if (keycode == 116) {
			event.keyCode = 0;
			event.returnValue = false;
			return false;
		}
	}
	else {
		if (keycode == 116) {
			return false;
		}
	}
}
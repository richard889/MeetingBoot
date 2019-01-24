/*
 * loads scripts one-by-one using recursion
 * returns jQuery.Deferred
 */
function loadScripts(scripts) {
	var deferred = jQuery.Deferred();	
	function loadScript(i) {
		if (i < scripts.length) {
			jQuery.ajax({
				url: scripts[i],
				dataType: "script",
				cache: true,
				success: function() {
					loadScript(i + 1);
				}
			});
        } else {
			deferred.resolve();
	    }
  	}

	loadScript(0);
	return deferred;
}

var d1 = loadScripts([
//	"/js/jquery.min.js",
//	"/js/jquery.datetimepicker.js",
//	"/js/bootstrap.min.js",
//	"/css/jquery.datetimepicker.min.css",
//	"/css/bootstrap.min.css"
]).done(function() {
	$("input[name='txtexceptDate']").datetimepicker({
		 format:'Y-m-d',
		 timepicker:false,
		// minDate:'-1970/01/02', //yesterday is minimum date
		// maxDate:'+1970/01/02' //tomorrow is maximum date
	});
	
	$("input[name='txtbeginTime']").datetimepicker({
		datepicker:false,
		format:'Hi',
	    minTime:'08:00',
	    maxTime:'20:00'
	});            	
	
	$("input[name='txtendTime']").datetimepicker({
		datepicker:false,
		format:'Hi',
	    minTime:'08:00',
	    maxTime:'20:00'
	});            	
	
});

// trigger a callback when all queues are complete
jQuery.when(d1).done(function() {
	console.log("All scripts loaded");
});
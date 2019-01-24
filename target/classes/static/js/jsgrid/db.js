(function() {

	var db = {

        loadData: function(filter) {
        	var json = $.parseJSON(this.data);
            return $.grep(json, function(client) {
                return (!filter.beginTime || client.beginTime.indexOf(filter.beginTime) > -1)
                    && (!filter.endTime || client.endTime.indexOf(filter.endTime) > -1);
                    //&& (!filter.Country || client.Country === filter.Country)
                    //&& (filter.Married === undefined || client.Married === filter.Married);
            });        	
        },

        insertItem: function(insertingClient) {
//        	this.clients.push(insertingClient);
        	var detail = {
	            	exceptDate: Date.parse(insertingClient.exceptDate),
	            	beginTime: insertingClient.beginTime,
	            	endTime: insertingClient.endTime,
	            	employee:{empNo: insertingClient.empNo},
	            	meeting:{meetingID: this.meetingID}
        		};        	
        	
	        $.ajax({
	        	url : "api/meetingdetail/addSave",
	            method: "post",
	            contentType: "application/json",
	            data: JSON.stringify(detail),             
	            success: function(result){
	            	alert("新增完成");
	            	return result;
	            },
	     		error : function(e) {
	     			alert("ERROR: ", e);
	     			console.log("ERROR: ", e);
	     		}
	        }); 
	        
        },

        updateItem: function(updatingClient) {
        	var detail = {
	            	id: updatingClient.id,
	            	empNo: updatingClient.empNo,
	            	exceptDate: Date.parse(updatingClient.exceptDate),
	            	beginTime: updatingClient.beginTime,
	            	endTime: updatingClient.endTime	            	
        		};       	
        	 
	        $.ajax({
	        	url : "api/meetingdetail/updateSave",
	            method: "post",
	            contentType: "application/json",
	            data: JSON.stringify(detail),             
	            success: function(result){
	            	alert("修改完成");
	            	return result;
	            },
	     		error : function(e) {
	     			alert("ERROR: ", e);
	     			console.log("ERROR: ", e);
	     		}
	        });

        },

        deleteItem: function(deletingClient) {
            var clientIndex = $.inArray(deletingClient, this.clients);
            this.clients.splice(clientIndex, 1);
        }
    };

    window.db = db;
    
    db.data = "";
    db.meetingID = "";

}());
// Edit pb button
$(document).ready(function() {
	$(".edit_pb").click(function() {
        
		var id = $(this).prop('name');
        
		var data = {
            "itemStatus":$('#itemStatus_val_' + id).val(),
            "status":$('#status_' + id).attr("value"),
            "pbId":$('#pbId_' + id).val(), 
            "receptId":$('#receptId_' + id).val(),
            }; 
      
        console.log(data);
        
      	jQuery.ajax({
			url : "api/pb/UpdatePB",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});

        }); 
    });
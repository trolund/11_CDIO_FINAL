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


// insert new pb button
$(document).ready(function() {
	$(".insert_pb").click(function() {
        
      
		var id = $(this).find('#pbId_');
        
		var data = {
            "itemStatus":$('#itemStatus_val_').val(),
            "status":$('#status_').attr("value"),
            "pbId":$('#pbId_').val(), 
            "receptId":$('#receptId_').val(),
            }; 
      
        console.log(data);
        
      	jQuery.ajax({
			url : "api/pb/insertPB",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		 boolNewRow = false;
                 loadpb(boolAcOnly);
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});

        }); 
    });


function tjekEditLock(){
    
    $('input').attr("readonly", boolEdit);
    
        console.log("edit check: " + boolEdit);
        
        if (boolEdit) {
            $("#table_con input").attr("disabled", true);
            $(".checkbox").attr("disabled", true);
            $(".selinput").attr("disabled", true);
            $(".edit_pb").attr("disabled", true);
        } else {
            $("#table_con input").removeAttr("disabled");
            $(".checkbox").removeAttr("disabled");
            $(".selinput").removeAttr("disabled");
            $(".edit_pb").removeAttr("disabled");

        }
    
} 
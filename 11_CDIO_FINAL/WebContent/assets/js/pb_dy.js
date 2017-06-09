// Edit pb button
$(document).ready(function() {
	$(".edit_pb").click(function() {
        
		var id = $(this).prop('name');
        
		var data = {
            "itemStatus":$('#itemStatus_val_' + id).val(),
            "status":$('#Status_val_' + id).val(),
            "pbId":$('#pbId_' + id).html(), 
            "receptId":$('#receptId_' + id).val(),
            }; 
      
         console.log(data);
        
      	jQuery.ajax({
			url : "api/pb/UpdatePB",
			data : JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success : function(data) { 
          		JSON.stringify(data + 'virker!')
			},
			error: function(jqXHR, text, error) { 
            	JSON.stringify(data)
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

// insert button
$(document).ready(function() {
  $('#Addpb_But').click(function() {
      addpb();
      insertsciped();
	}); 
});

function insertsciped(){
    // insert new pb button
	$(".insert_pb").click(function() {
        
		var data = {
            "itemStatus":$('#itemStatus_val_').val(),
            "status":$('#status_').attr("value"),
            "pbId":$('#pbId_').val(), 
            "receptId":$('#receptId_').val(),
            }; 
    
        console.log(data);
        
      	jQuery.ajax({
			url : "api/pb/insertPB",
			data : JSON.stringify(data),
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

}
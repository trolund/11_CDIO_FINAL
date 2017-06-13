// Edit material button
$(document).ready(function() {
	$(".edit_materialb").click(function() {
        
		var id = $(this).prop('name');
        
		var data = {
            "rbId":$('#rbId_' + id).html(),
            "status":$('#Status_val_' + id).val(),
            "raavareId":$('#Mat_val_' + id).val(), 
            "amount":$('#maengde_' + id).val(),
            }; 
      
         console.log(data);
        
      	jQuery.ajax({
			url : "api/mb/Update",
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

function tjekEditLock() {
    $('input').attr("readonly", boolEdit);
    console.log("edit check: " + boolEdit);
  
    if (boolEdit) {
    	$("#table_con input").attr("disabled", true);
    	$(".checkbox").attr("disabled", true);
    	$(".selinput").attr("disabled", true);
    	$(".edit_recept").attr("disabled", true);
    } else {
        $("#table_con input").removeAttr("disabled");
        $(".checkbox").removeAttr("disabled");
        $(".selinput").removeAttr("disabled");
        $(".edit_recept").removeAttr("disabled");
    }
} 

function insertsciped(){
    // insert new material button
	$(".insert_materialb").click(function() {
        
		var data = {
            "rbId":$('#rbId_').val(),
            "status":"0",
            "raavareId":$('#Mat_val_').val(), 
            "amount":$('#meangde_').val(),
            }; 
    
        console.log(data);
        
      	jQuery.ajax({
			url : "api/mb/insert",
			data : JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
                 loadmaterial(boolAcOnly);
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});
	}); 
}
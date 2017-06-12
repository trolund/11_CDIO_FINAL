// Edit material button
$(document).ready(function() {
	$(".edit_material").click(function() {
		var id = $(this).prop('name');
        
		var data = {
            "Supplier":$('#Supplier_' + id).val(),
            "status":$('#Status_val_' + id).val(),
            "raavareId":$('#raavareId_' + id).html(), 
            }; 
      
        console.log(data);
        
      	jQuery.ajax({
			url : "api/material/Update",
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
	$(".insert_material").click(function() {
		var data = {
				"receptName":$('#levenradoer_').val(),
            	"status": "0",
            	"receptId":$('#materialName_').val(), 
            }; 
   
        console.log(data);
        
      	jQuery.ajax({
			url : "api/recept/insert",
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
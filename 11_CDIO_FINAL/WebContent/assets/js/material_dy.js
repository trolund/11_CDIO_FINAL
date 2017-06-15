// Edit material button
$(document).ready(function() {
	$(".edit_material").click(function() {
		var id = $(this).prop('name');
        
		var data = {
            "supplier":$('#Supplier_' + id).val(),
            "status":$('#Status_val_' + id).val(),
            "raavareId":$('#raavareId_' + id).html(), 
            "raavareName":$('#materialName_' + id).val()
            }; 
      
        console.log(data);
        
      	jQuery.ajax({
			url : "api/material/Update",
			data : JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success : function(data) { 
          		showPopup("Material [" + $('#raavareId_' + id).html() + "] was updated.", true);
			},
			error: function(jqXHR, text, error) { 
            	showPopup("Material [" + $('#raavareId_' + id).html() + "] wasn't updated.", false);
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
				"supplier":$('#levenradoer_').val(),
            	"status": "0",
            	"raavareName":$('#materialName_').val(), 
                "raavareId":$('#materialId_').val()
            }; 
   
        console.log(data);
        
      	jQuery.ajax({
			url : "api/material/insert",
			data : JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
                 loadmaterial(boolAcOnly);
             	showPopup("Material [" + $('#raavareId_' + id).html() + "] was created.", true);
			},
			error: function(jqXHR, text, error) { 
            	showPopup("Material [" + $('#raavareId_' + id).html() + "] wasn't created.", false);
			}
	    });
    }); 
}
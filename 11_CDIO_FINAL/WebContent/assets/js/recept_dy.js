// Edit recept button
$(document).ready(function() {
	$(".edit_recept").click(function() {
        
		var id = $(this).prop('name');
        
		var data = {
            "receptName":$('#receptNavn_' + id).val(),
            "status":$('#Status_val_' + id).val(),
            "receptId":$('#receptId_' + id).html(), 
            }; 
      
        console.log(id);
         console.log(data);
        
      	jQuery.ajax({
			url : "api/recept/Update",
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
    // insert new recept button
	$(".insert_recept").click(function() {
        
		var data = {
            "receptName":$('#receptName_').val(),
            "status": "0",
            "receptId":$('#receptId_').val(), 
            }; 
    
        console.log(data);
        
      	jQuery.ajax({
			url : "api/recept/insert",
			data : JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
                 loadrecept(boolAcOnly);
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});

        }); 

}


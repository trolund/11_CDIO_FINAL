$(document).ready(function() {
            $('#oprIdEdit').val(user.oprId);
        	$('#oprFirstNameEdit').val(user.oprFirstName); 
        	$('#oprLastNameEdit').val(user.oprLastName);
        	$('#oprIniEdit').val(user.oprIni);
        	$('#oprEmailEdit').val(user.oprEmail);
        	$('#oprCprEdit').val(user.oprCpr);
}); 


//Post Update user data
$(document).ready(function() {
	$("#Submit_profil_settings").click(function() {
		var data = $('#profilForm').serializeJSON();    
    	console.log(data);
      
      	jQuery.ajax({
			url : "api/opr/updateopr",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		console.log(data);
          	  	$('#Editmsg').html(data);
			},
			error: function(jqXHR, text, error) { 
            	console.log(data);
            	$('#Editmsg').html(data);
			}
		});  
	});   
});
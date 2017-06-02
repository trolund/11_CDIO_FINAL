// Delete users 
$(document).ready(function() {
	$('.del_user').click(function() {
		
       var id = $(this).prop('name');
       console.log('Try to delete user with id: ' + id);  
       
       jQuery.ajax({
    	   url: "api/opr/deleteOpr",
    	   type: "POST",
    	   data : id,
    	   contentType: "application/json",
    	   
    	   success: function(resultData) {
    		   if (resultData == "true") {
    			   console.log('User successfully deleted.' + 'return:' + resultData);
    			   $('tr#' + id + ' td:first-child').html("<td style='color: red;'>Inactive</td>").fadeIn(200);
                   
    		   } else {
    			   console.log('User NOT deleted.' + 'return:' + resultData);
    			   $('#' + id).css('background-color', 'yellow').fadeIn(400); 
    		   }
    	   },
    	   
    	   error : function(jqXHR, textStatus, errorThrown) {
    		   console.log("Id [" + id + "] does not exist.");
    	   },
    	   
    	   timeout: 120000,
       });
    }); 
});

// Edit user button
$(document).ready(function() {
	$(".edit_user").click(function() {
		var id = $(this).prop('name');
        
		$.getJSON('api/opr/RoleList/' + id, function(roleData) { 
			$('#AdminRoleEdit').prop('checked', false);
            $('#FarRoleEdit').prop('checked', false);
            $('#VeakRoleEdit').prop('checked', false);
            $('#LabRoleEdit').prop('checked', false);
            
        	$.each(roleData, function(i, item) {	
        		if (roleData[i].roleName == "Admin") {
        			$('#AdminRoleEdit').prop('checked', true);
        		}
            
        		if (roleData[i].roleName == "Farmaceut") {
        			$('#FarRoleEdit').prop('checked', true);
        		}
            
        		if(roleData[i].roleName == "Værkfører") {
        			$('#VeakRoleEdit').prop('checked', true);
        		}
            
        		if (roleData[i].roleName == "Laborant") { 
        			$('#LabRoleEdit').prop('checked', true);
        		}           
        	});
        });    
        
        $.getJSON('api/opr/' + id, function(data) {
        	console.log('User with id: ' + id + " loaded for edit");
        	console.log(data);
            
        	$('#statusEdit').val(data.status);
            $('#oprIdEdit').val(data.oprId);
        	$('#oprFirstNameEdit').val(data.oprFirstName); 
        	$('#oprLastNameEdit').val(data.oprLastName);
        	$('#oprIniEdit').val(data.oprIni);
        	$('#oprEmailEdit').val(data.oprEmail);
        	$('#oprCprEdit').val(data.oprCpr);
            
            console.log(data + "added to form")
        }); 
        
        $("#editUser_Box").show(400);
    });
});

$(document).ready(function(){
    $('#newPassword_But').click(function(){
        var id = $('#oprIdEdit').val();
        console.log(id);
        $.post("api/mail/newPass/" + id, function( data ) {
        	console.log(data); 
            $('#Editmsg').html("Mail med nyt kodeord sent!");
        });
    })
});

//Post Update user data
$(document).ready(function() {
	$("#Submit_EditUser").click(function() {
		var data = $('#editUserForm').serializeJSON();    
    	console.log(data);
      
      	jQuery.ajax({
			url : "api/opr/updateopr",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		console.log(data);
          	  	$('#Editmsg').html(data);
           	 	loadUsers();
			},
			error: function(jqXHR, text, error) { 
            	console.log(data);
            	$('#Editmsg').html(data).fadeToggle(500);
             	$("#editUser_Box").show(400);
			}
		});  
	});   
});


// Add user button
$(document).ready(function() {
	$("#Adduser_But").click(function() {
    	$("#AddUser_Box").show(400);
	}); 
});

// Cancel icon create user box
$(document).ready(function() { 
	$(".cancel_Icon").click(function() {
        
        var action = $(this).prop('name');
        
        console.log(action);
        
        if(action == 'edit'){
		  $("#editUser_Box").hide(400);
        }
        else{ 
          $("#AddUser_Box").hide(400);
        } 
    }); 
});

// Post create user data
$(document).ready(function() {
	$("#Submit_CreateUser").click(function() {
		var data = $('#CreateUserForm').serializeJSON();    
    	console.log(data);
      
      	jQuery.ajax({
			url : "api/opr/addopr",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		console.log(data);
          	  	$('#CreateMSG').html(data);
           	 	loadUsers();
			},
			error: function(jqXHR, text, error) { 
            	$('#CreateMSG').html(data);
             	$("#AddUser_Box").show(400);
			}
		});  
	});   
});

// Refresh button
$(document).ready(function() {
	$("#refresh_But").click(function() { 
    	console.log('Refreshing users.'); 
    	loadUsers();
	});
});

// Create dummy user  
$(document).ready(function() {
	$("#Submit_CreateUser_dummy").click(function() {
		
		$('#oprId').val(Math.floor((Math.random() * 900) + 100));
        $('#oprFirstName').val('Dum');
        $('#oprLastName').val('Dummy');
        $('#oprIni').val('DD');
        $('#oprEmail').val('dum@dummy.com');
        $('#oprCpr').val('oprCpr');
        $('#oprPassword').val('pass');
        $('#oprRole1').val('None');
        
        // Adding roles to dummy user.
        $('#adminRole').prop('checked', true);
        $('#farmaceutRole').prop('checked', true);
        $('#vaerkRole').prop('checked', true);
        $('#laborantRole').prop('checked', true);
        
        var data = $('#CreateUserForm').serializeJSON();    
      
      	jQuery.ajax({
			url : "api/opr/addopr",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
            	console.log(data);
            	$('#CreateMSG').html(data);
           		loadUsers();
			},
			error: function(jqXHR, text, error){ 
           		console.log(data);
            	$('#CreateMSG').html(data).fadeToggle(500);
            	$("#AddUser_Box").show();
			}
		}); 
  	});   
});
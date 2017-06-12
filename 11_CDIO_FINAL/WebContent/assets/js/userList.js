$(document).ready(function(){
    $('#newPassword_But').click(function(){
        var id = $('#oprIdEdit').val();
        console.log(id);
        $.post("api/mail/newPass/" + id, function( data ) {
        	console.log(data); 
            $('#Editmsg').html("E-mail sent successfully.");
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
        
        if (action == 'edit') {
        	$("#editUser_Box").hide(400);
        } else { 
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
        $('#oprCpr').val('1234567890');
        $('#oprPassword').val('password');
        $('#oprRole1').val('None');
        
        // Adding roles to dummy user.
        $('#adminRole').prop('checked', true);
        $('#farmaceutRole').prop('checked', true);
        $('#værkførerRole').prop('checked', true);
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
			error: function(jqXHR, text, error) { 
           		console.log(data);
            	$('#CreateMSG').html(data).fadeToggle(500);
            	$("#AddUser_Box").show();
			}
		}); 
  	});   
});
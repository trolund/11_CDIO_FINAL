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
        			
            if(roleData[i].roleName == "Admin"){
                $('#AdminRoleEdit').prop('checked', true);
            }
            if(roleData[i].roleName == "Farmaceut"){
                $('#FarRoleEdit').prop('checked',true);
            }
            if(roleData[i].roleName == "Værkfører"){
                $('#VeakRoleEdit').prop('checked',true);
            }
            if(roleData[i].roleName == "Laborant"){ 
                $('#LabRoleEdit').prop('checked',true);
            }
                        
        	});
        });    
        
        
        $.getJSON('api/opr/' + id, function(data) {
        	console.log('User with id: ' + id + "loaded for edit");
        	console.log(data);
            
        	$('#statusEdit').val(data.status);
        	$('#oprIdEdit').html(data.oprId);
        	$('#oprFirstNameEdit').val(data.oprFirstName); 
        	$('#oprLastNameEdit').val(data.oprLastName);
        	$('#oprIniEdit').val(data.oprIni);
        	$('#oprEmailEdit').val(data.oprEmail);
        	$('#oprCprEdit').val(data.oprCpr);
            console.log('Checkbox input' + data.adminRole)
        	
            
        	console.log(data + "added to form")
        }); 
        
        $("#editUser_Box").show(400);
    });
});
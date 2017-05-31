// Edit user button
$(document).ready(function() {
    $(".edit_user").click(function() {
    	var id = $(this).prop('name');
        console.log(id);
        
        $.getJSON('api/opr/RoleList/' + id, function(roleData) {            
            console.log(roleData);
            
        	$.each(roleData, function(i, item) {
        			
            if(item.roleName == "Admin"){
                 $('#AdminRoleEdit').prop('Checked', true);
            }
            else if(item.roleName == "Farmaceut"){
                $('#FarRoleEdit').prop('Checked',true);
            }
            else if(item.roleName == "Værkfører"){
                $('#VeakRoleEdit').prop('Checked',true);
            }
            else if(item.roleName == "Laborant"){ 
            $('#laborantRoleEdit').prop('Checked',true);
            }
            else{
                $('#Editmsg').html('ingen roller fra fundet.')
            }
                     
                
        	});
        });
        
        $.getJSON('api/opr/' + id, function(data) {
        	console.log('User with id: ' + id + "loaded for edit");
        	console.log(data);
        	
        	$('#oprIdEdit').val(data.oprId);
        	$('#oprFirstNameEdit').val(data.oprName); 
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
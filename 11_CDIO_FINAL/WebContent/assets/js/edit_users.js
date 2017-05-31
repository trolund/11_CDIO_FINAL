// Edit user button
$(document).ready(function() {
    $(".edit_user").click(function() {
    	var id = $(this).prop('name');
        console.log(id);
        
        $.getJSON('api/opr/RoleList/' + id, function(roleData) {            
            console.log(roleData);
            
        	$.each(roleData, function(i, item) {
        			
            if(roleData[i].roleName == "Admin"){
                 $('#AdminRoleEdit').prop('Checked', true);
                console.log('han er admin!!!!')
            }
            if(roleData[i].roleName == "Farmaceut"){
                $('#FarRoleEdit').prop('Checked',true);
            }
            if(roleData[i].roleName == "Værkfører"){
                $('#VeakRoleEdit').prop('Checked',true);
            }
            if(roleData[i].roleName == "Laborant"){ 
            $('#laborantRoleEdit').prop('Checked',true);
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
// edit user button
$(document).ready(function() {
    $(".edit_User").click(function(){
        
        var id = $(this).prop('name');
        
        console.log(id);
        
        $.getJSON('RoleList/' + id, function(roleData) {            
            $.each(roleData, function(i, item) {
                if(roleData != null){
                    var string = roleData[i].toString();
                $('#oprRole'+(i+1)).val(string.toLowerCase());
                    console.log('DEBUG ROLE-LOOP: i: ' + (i+1) +', data: '+ roleData[i]);
                }
	    });
    });
        
        $.getJSON('api/opr/' + id, function(data) {
            
	    console.log('User with id: ' + id + "loaded for edit");
            
        console.log(data);
        
        $('#oprId').val(data.oprId);
        $('#oprName').val(data.oprName); 
        $('#oprIni').val(data.oprIni);
        $('#oprCpr').val(data.oprCpr);
        $('#oprPassword').val("");
        
        console.log(data + "added to form")
    }); 
        $("#AddUser_Box").show(400);
});
  });  




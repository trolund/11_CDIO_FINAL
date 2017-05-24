
//Delete users 
$(document).ready(function(){
   $('.del_User').click(function(){
       
        var id = $(this).prop('name');

        console.log('Try to delete user with id: ' + id);  
        
     jQuery.ajax({
	  url: "api/opr/deleteOpr",
	  type: "POST",
      data : id,
	  contentType: "application/json",
	  success: function(resultData) {
        if(resultData == "true"){
            console.log('User successfully deleted.' + 'return:' + resultData);
         $('#' + id).hide(400);
        }
        else{
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

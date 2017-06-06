$(document).ready(function() {
	// toggle pb button 
	$('#checkbox_val').click(function() {
		console.log("check");
        var bool = $('#checkbox_val').is(":checked");
		$('input').attr("readonly", bool);
        
//        if (bool) {
//            $("input").removeAttr("disabled");
//        } else {
//            $("input").attr("disabled", true);
//        }
        
	});
});


// Edit user button
$(document).ready(function() {
	$(".edit_pb").click(function() {
        
		var id = $(this).prop('data');
        
        console.log("click:" + id);
        
		var data = $('#' + id).serializeJSON();    
        
    	console.log(data);
      
      	jQuery.ajax({
			url : "api/pb/",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});
        }); 
    });

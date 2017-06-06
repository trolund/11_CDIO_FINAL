$(document).ready(function() {
	// toggle pb button 
	$('#checkbox_val').click(function() {
		console.log("check");
        var bool = $('#checkbox_val').is(":checked");
        
		$('#table_con > input').attr("readonly", bool);
        
        if (bool) {
            $("#table_con > input").removeAttr("disabled");
        } else {
            $("#table_con > input").attr("disabled", true);
        }
        
	});
});

// Edit user button
$(document).ready(function() {
	$(".edit_pb").click(function() {
        
		var id = $(this).prop('name');
        
		var data = {
            itemStatus: $('#itemStatus_val_' + id).val(),
            status: $('#status_' + id).val(),
            pbId: $('#pbId_' + id).val(),
            receptId: $('#receptId_' + id).val(),
        }
      
        console.log(data);
        
      	jQuery.ajax({
			url : "api/pb/UpdatePB",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});
        
        return false;
        }); 
    });
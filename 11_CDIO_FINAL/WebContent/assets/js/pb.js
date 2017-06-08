var boolEdit = false;
var boolAcOnly = false;

$(document).ready(function() {
	// toggle pb button 
	$('#checkbox_val').click(function() {
		
        boolEdit = $('#checkbox_val').is(":checked");
		$('input').attr("readonly", boolEdit);
        console.log("edit check: " + boolEdit);
        
        if (boolEdit) {
            $("#table_con input").attr("disabled", true);
            $(".checkbox").attr("disabled", true);
            $(".selinput").attr("disabled", true);
            $(".edit_pb").attr("disabled", true);
        } else {
            $("#table_con input").removeAttr("disabled");
            $(".checkbox").removeAttr("disabled");
            $(".selinput").removeAttr("disabled");
            $(".edit_pb").removeAttr("disabled");
        } 
	});
});

$(document).ready(function() {
  $('#checkbox_val_ac').click(function() {
        boolAcOnly = $('#checkbox_val_ac').is(":checked");
        loadpb(boolAcOnly);
	});
});

$(document).ready(function() {
 $('#refresh_But').click(function() {
		loadpb(boolAcOnly);
	}); 
});

// Edit pb button
$(document).ready(function() {
	$(".edit_pb").click(function() {
        
		var id = $(this).prop('name');
        
		var data = {
            "itemStatus":$('#itemStatus_val_' + id).val(),
            "status":$('#status_' + id).attr("value"),
            "pbId":$('#pbId_' + id).val(), 
            "receptId":$('#receptId_' + id).val(),
            }; 
      
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
	}); 
});

//del pb button
$(document).ready(function() {
	$("#pb_del_But").click(function() {    
		var data;
        $('#table_con tr').each(function() {
            $(this).find('td input[type="checkbox"]').each(function() {
            	var checkedValue = $(this).is(":checked");
            	var pbId = $(this).prop('name');
                
                console.log('id: ' + pbId + ' value: ' + checkedValue);
                
                if (checkedValue) {
                    jQuery.ajax({
                        url : "api/pb/delPB/" + pbId,
                        method: 'POST',
                        success : function(data) {
                        	console.log('id: ' + pbId  + ' value: ' + checkedValue + 'was deleted');
                            console.log(data);
                        },
                        error: function(jqXHR, text, error) { 
                        	console.log('id: ' + pbId + ' value: ' + checkedValue + 'was delete failed');
                            console.log(data);
                        }
                    });         
                }
            });
        });
        loadpb(boolAcOnly);
    }); 
});
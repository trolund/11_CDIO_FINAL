var boolEdit = false;
var boolAcOnly = false;
var boolNewRow = false;

$(document).ready(function() {
	//edit toggle pb button 
	$('#checkbox_val').click(function() {	
        boolEdit = $('#checkbox_val').is(":checked");
        
        tjekEditLock();
	});

});


function tjekEditLock(){
    
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
    
} 


$(document).ready(function() {
  $('#checkbox_val_ac').click(function() {
      
      $('#table_con').empty(); 
      
        boolAcOnly = $('#checkbox_val_ac').is(":checked");
        
        loadpb(boolAcOnly);
	});
    
});


$(document).ready(function() {
  $('#Addpb_But').click(function() {
      addpb();
	});
    
});

$(document).ready(function() {
 $('#refresh_But').click(function() {
     
     $('#table_con').empty(); 
     
		loadpb(boolAcOnly);
	}); 
});


//del pb button
$(document).ready(function() {
	$("#pb_del_But").click(function() {
    
		var data;
        
        $('#table_con tr').each(function(){
            $(this).find('td input[type="checkbox"]').each(function(){
                
            var checkedValue = $(this).is(":checked");
            var pbId = $(this).prop('name');
                
                console.log('id: ' + pbId + ' value: ' + checkedValue);
                
                if(checkedValue){
                    
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

function addpb(){
    
    if(!boolNewRow){
    var itemStatus = "<select class='selinput' name='itemStatus' id='itemStatus_val_'><option value='0'>Ikke påbegyndt</option><option value='1'>Under produktion</option><option value='2'>Afsluttet</option></select>"
    
    $("#table_con tr:first-child").after('<tr name="" id="row">' + '<td value="0" id="status_" style="color: green;">Active</td>' + '<td><input id="pbId_" type="text" value=""></td>' + '<td>' + itemStatus + '</td>' + '<td><input id="receptId_" type="text" value=""></td>' + '<td></td>' + '<td><button class="insert_pb" name="">insert</button></td>' + '</tr>');
    }
    else {
        // fejl i popup
    }
     boolNewRow = true;
}

// insert new pb button
$(document).ready(function() {
	$(".insert_pb").click(function() {
        
      
		var id = $(this).find('#pbId_');
        
		var data = {
            "itemStatus":$('#itemStatus_val_').val(),
            "status":$('#status_').attr("value"),
            "pbId":$('#pbId_').val(), 
            "receptId":$('#receptId_').val(),
            }; 
      
        console.log(data);
        
      	jQuery.ajax({
			url : "api/pb/insertPB",
			data : data,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
          		 boolNewRow = false;
                 loadpb(boolAcOnly);
			},
			error: function(jqXHR, text, error) { 
            	
			}
		});

        }); 
    });
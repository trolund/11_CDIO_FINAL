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


$(document).ready(function() {
  $("#checkbox_val_ac").click(function() {
      
      $('#table_con').empty(); 
      
        boolAcOnly = $('#checkbox_val_ac').is(":checked");
        loadpb(boolAcOnly);
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
                        	console.log('Product Batch [' + pbId  + '] value' + checkedValue + 'was deleted');
                            console.log(data);
                        	showPopup("Product Batch [" + pbId + "] was set inactive.", true);
                        },
                        error: function(jqXHR, text, error) { 
                        	console.log('id: ' + pbId + ' value: ' + checkedValue + 'was delete failed');
                            console.log(data);
                            showPopup("Product Batch [" + pbId + "] wasn't set inactive.", true);
                        }
                    });         
                }
            });
        });
        loadpb(boolAcOnly);
        }); 
    });

function addpb(){
    
    var itemStatus = "<select class='selinput' name='itemStatus' id='itemStatus_val_'><option value='0'>Ikke påbegyndt</option><option value='1'>Under produktion</option><option value='2'>Afsluttet</option></select>"
    
    $("#table_con tr:first-child").after('<tr name="" id="row">' + '<td value="0" id="status_" style="color: green;">Active</td>' + '<td><input id="pbId_" type="text" value=""></td>' + '<td>' + itemStatus + '</td>' + '<td>'+ '<select class="selinput" id="receptId_">' + options + '</select></td>' + '<td></td>' + '<td><button class="insert_pb" name="">insert</button></td>' + '</tr>');

     
}

// add pb button
$(document).ready(function() {
  $('#Addpb_But').click(function() {
      addpb();
      insertsciped();
	}); 
});
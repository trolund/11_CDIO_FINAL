var boolEdit = false;
var boolAcOnly = false;
var boolNewRow = false;

$(document).ready(function() {
	//edit toggle recept button 
	$('#checkbox_val').click(function() {	
        boolEdit = $('#checkbox_val').is(":checked");
        
        tjekEditLock();
	});

});


$(document).ready(function() {
  $('#checkbox_val_ac').click(function() {
      
      $('#table_con').empty(); 
      
        boolAcOnly = $('#checkbox_val_ac').is(":checked");
        loadrecept(boolAcOnly);
	});
});


$(document).ready(function() {
 $('#refresh_But').click(function() {
     
     $('#table_con').empty(); 
     
		loadrecept(boolAcOnly);
	}); 
});


//del recept button
$(document).ready(function() {
	$("#recept_del_But").click(function() {    
		var data;
        $('#table_con tr').each(function() {
            $(this).find('td input[type="checkbox"]').each(function() {
            	var checkedValue = $(this).is(":checked");
            	var Id = $(this).prop('name');
                
                console.log('id: ' + Id + ' value: ' + checkedValue);
                
                if (checkedValue) {
                    jQuery.ajax({
                        url : "api/recept/del/" + Id,
                        method: 'POST',
                        success : function(data) {
                        	console.log('id: ' + Id  + ' value: ' + checkedValue + 'was deleted');
                            showPopup("Receipt [" + Id + "] was set inactive.", true);
                        },
                        error: function(jqXHR, text, error) { 
                        	console.log('id: ' + Id + ' value: ' + checkedValue + 'was delete failed');
                            showPopup("Receipt [" + Id + "] wasn't set inactive.", false);
                        }
                    });         
                }
            });
        });
        loadrecept(boolAcOnly);
        }); 
    });
	
function addre(){
    $("#table_con tr:first-child").after('<tr name="" id="row">' + '<td value="0" id="status_" style="color: green;">Active</td>' + '<td><input id="receptId_" type="text" value=""></td>' + '<td><input id="receptName_"></td>' + '<td></td>' + '<td><button class="insert_recept" name="">insert</button></td>' + '</tr>');
}

// add recept button
$(document).ready(function() {
  $('#Addrecept_But').click(function() {
      addre();
      insertsciped();
	}); 
});
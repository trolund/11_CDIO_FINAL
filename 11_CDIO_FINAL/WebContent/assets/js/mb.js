var boolEdit = false;
var boolAcOnly = false;
var boolNewRow = false;

$(document).ready(function() {
	//edit toggle material button 
	$('#checkbox_val').click(function() {	
        boolEdit = $('#checkbox_val').is(":checked");
        
        tjekEditLock();
	});

});


$(document).ready(function() {
  $('#checkbox_val_ac').click(function() {
      
      $('#table_con').empty(); 
      
        boolAcOnly = $('#checkbox_val_ac').is(":checked");
        loadmb(boolAcOnly);
	});
});


$(document).ready(function() {
 $("#refresh_But").click(function() {
     
     $('#table_con').empty(); 
     
		loadmb(boolAcOnly);
	}); 
});


//del material button
$(document).ready(function() {
	$("#material_del_But").click(function() {    
		var data;
        $('#table_con tr').each(function() {
            $(this).find('td input[type="checkbox"]').each(function() {
            	var checkedValue = $(this).is(":checked");
            	var Id = $(this).prop('name');
                
                console.log('id: ' + Id + ' value: ' + checkedValue);
                
                if (checkedValue) {
                    jQuery.ajax({
                        url : "api/mb/del/" + Id,
                        method: 'POST',
                        success : function(data) {
                        	console.log('id: ' + Id  + ' value: ' + checkedValue + 'was deleted');
                            showPopup("Material Batch [" + Id + "] was set inactive.", true);
                        },
                        error: function(jqXHR, text, error) { 
                        	console.log('id: ' + Id + ' value: ' + checkedValue + 'was delete failed');
                            showPopup("Material Batch [" + Id + "] wasn't set inactve.", false);
                        }
                    });         
                }
            });
        });
        loadmb(boolAcOnly);
        }); 
    });

function addre(){
    
    $("#table_con tr:first-child").after('<tr name="" id="row">' + '<td value="0" id="status_" style="color: green;">Active</td>' + '<td><input id="rbId_" type="text" value=""></td>' + '<td><select class="selinput" id="Mat_val_">' + matOptions + '</select><td><input id="meangde_">' + '</td>' + '<td></td>' + '<td><button class="insert_materialb" name="">insert</button></td>' + '</tr>');

     
}

// add material button
$(document).ready(function() {
  $('#Addmaterialb_But').click(function() {
      addre();
      insertsciped();
	}); 
});

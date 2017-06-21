//// view display
//function viewToHTML(data) {
//	$('#table_con').empty();
//	$.each(data, function (index, item) {
//		var eachrow = "<tr>";
//	    eachrow.append("<td>" + item[1] + "</td>");             
//        
//	    for (i = 1; i < item.length; i++) { 
//	    	if(item[i + 1] != null) {
//	    		eachrow.append("<td>" + item[i] + "</td>");
//	    	}
//	    } 
//	                 
//	    eachrow.append("</tr>");
//	    $('#table_con').append(eachrow);
//	});
//}

var ErrorMSG = "noget gik galt!"  

$(document).ready(function(){
    $('#viewSelector').change(function() {
    if ($(this).val() === '1') {
        AdminForemanview();
    } else if ($(this).val() === '2') {  
        operator_rb();
    } else if ($(this).val() === '3') {  
        AdminOperator();
    } else if ($(this).val() === '4') {   
        ForemanOprator();
    } else if ($(this).val() === '5') {  
        AdminOperator();
    } else {
        $('#table_con').empty();
        $('#table_con').append("intet view")
    }
});
    
});

function AdminForemanview(){
    
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>opr_id</td><td>opr_navn</td><td>pb_id</td><td>rb_id</td><td>tara</td><td>netto</td><td>status</td></tr>');
    
    jQuery.ajax({ 
    	url: "api/View/AdminForemanList",
        type: "GET",
        contentType: 'text/plain',
        success: function(data) {  
        	$.each(data, function(i, item) { 
        		$('#table_con').append('<tr><td>' + data[i].opr_id + '</td><td>' + data[i].opr_navn + '</td><td>' + data[i].pb_id + '</td><td>' + data[i].rb_id + '</td><td>' + data[i].tara + '</td><td>' + data[i].netto + '</td><td>' + data[i].status + '</td></tr>');
            })
        			
        },
        error : function(jqXHR, textStatus, errorThrown) { 
        	$('#table_con').append(ErrorMSG);
        }
    }) 
    console.log('AdminForeman view loaded');
}

function operator_rb(){  
    
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>raavare_id</td><td>rb_id</td><td>mængde</td><td>raavare_navn</td><td>leverandør</td></tr>');
    
    jQuery.ajax({
    	url: "api/View/OperatorRBList",
        type: "GET",
        contentType: 'text/plain',
        success: function(data) {  
        	console.log(data);
            $.each(data, function(i, item) { 
            	$('#table_con').append('<tr><td>' + data[i].raavareId + '</td><td>' + data[i].rbId + '</td><td>' + data[i].maengde + '</td><td>' + data[i].raavareName + '</td><td>' + data[i].supplier + '</td></tr>'); 
            })
        			
        },
        error : function(jqXHR, textStatus, errorThrown) { 
        		$('#table_con').append(ErrorMSG);
        }
    }) 
    console.log('operator_rb view loaded');
}

function AdminOperator(){ 
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>oprCpr</td><td>oprid</td><td>oprIni</td><td>oprName</td><td>OprPasword</td><td>oprRoles</td></tr>');
    
    jQuery.ajax({
        		url: "api/View/AdminOperatorList",
        		type: "GET",
        		contentType: 'text/plain',
        		success: function(data) {  
                    
                    console.log(data);
                    
                     $.each(data, function(i, item) { 
                     
                     $('#table_con').append('<tr><td>' + data[i].oprCpr + '</td><td>' + data[i].oprId + '</td><td>' + data[i].oprIni + '</td><td>' + data[i].oprName + '</td><td>' + data[i].oprPassword + '</td><td>' + data[i].oprRoles +'</td></tr>'); 
                     
                     })
        			
        		},
        		error : function(jqXHR, textStatus, errorThrown) { 
        			 $('#table_con').append(ErrorMSG);
        		}
        	}) 
    console.log('operator_rb view loaded');
}

function ForemanOprator(){ 
    
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>oprCpr</td><td>oprid</td><td>oprIni</td><td>oprName</td><td>oprRoles</td></tr>');
    
    
    jQuery.ajax({
        		url: "api/View/FormanOperatorList",
        		type: "GET",
        		contentType: 'text/plain',
        		success: function(data) {  
                    
                    console.log(data);
                    
                     $.each(data, function(i, item) { 
                     
                     $('#table_con').append('<tr><td>' + data[i].oprCpr + '</td><td>' + data[i].oprId + '</td><td>' + data[i].oprIni + '</td><td>' + data[i].oprName + '</td><td>' + data[i].oprRoles +'</td></tr>'); 
                     
                     })
        			
        		},
        		error : function(jqXHR, textStatus, errorThrown) { 
        			 $('#table_con').append(ErrorMSG);
        		}
        	}) 
    console.log('operator_rb view loaded');
}
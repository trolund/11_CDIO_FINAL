var id = null;
var userName = null;
var roles = null;

var authHeader = "Bearer " + localStorage.getItem("jwt");

// Alt der skal køres ved opstart af application!
$(document).ready(function(){
	$('#loadingGif').hide();
    $('#AddUser_Box').hide();
});

// LogOut button menu  
$(document).ready(function() {
	$("#logOut_but").click(function() {
		//var id = null;
		//var user = null;
		//var roles = null;
      
		$('#msg').html('');
		$('#login_Bg').show(1000);  
		$('#oprFirstName').html('Username').delay(1000);
		$('#oprRoles').html('Rolle, rolle').delay(1000);
		$('#content_box').html('').delay(1000); 
		// window.location.reload(); måske skal den bruges....
	});
});

// Post login data
$(document).ready(function() {
	$("#login_but").click(function() {  
		
		id = $('#login_oprId').val();
		$('#login_Bg').show();
		$('#loadingGif').show(200);
      
		var postData = $('#login').serializeJSON();    
      
		jQuery.ajax({
			url : "api/opr/verify",
			data : postData,
			contentType: "application/json",
			method: 'POST',
			success : function(data) {
				if (data == 'Logged in successfully.') {
					$('#msg').css('color','green');
					$('#msg').html(data);
					$('#login_Bg').hide(1000);     
					$('#login_Bg').hide(200);
					$('#loadingGif').hide(200);
 
					loadLoginUser(id);
					roles.search("Admin");
				} else {
					$('#msg').css('color','red');
					$('#msg').html(data);
					$('#login_Bg').show();
					$('#loadingGif').hide(200);
					
					console.log('Failed to log in - ' + data)
				}
			},
			error: function(jqXHR, text, error) {  
				$('#msg').css('color','red');
                $('#msg').html('Server fail');
                $('#loadingGif').hide(200);
                
                console.log('Failed to log in - ' + data)
			}
		}
	)} 
)});

// Menu mobile button 
$(document).ready(function() {
	$("#menu_but").click(function() {
		$("#sidepanel ul").toggleClass("on");
		$("#sidepanel").toggleClass("on_sidepanel");
		$("#menu_but").toggleClass("rot");
	});
});

// User button menu 
$(document).ready(function() {
	$("#user_but").click(function() {       
		$("#content_box").load('add_user.html');
		$('#AddUser_Box').hide();
		loadUsers();
	});
});

// Hent liste af users og oversæt dem til tabel
function loadUsers(){
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>Status</td><td>Id</td><td>First Name</td><td>Last Name</td><td>Initials</td><td>E-mail</td><td>Cpr</td><td>Roles</td><td>Delete</td><td>Edit</td></tr>');
    
    $.getJSON('api/opr/getOprList', function(data) {
	    console.log('Users loaded.');
	    
	    var roles;
        var status;
	
        $.each(data, function(i, item) { 
             
        	jQuery.ajax({
        		url: "api/opr/getOprRoleList/" + data[i].oprId,
        		type: "GET",
        		contentType: 'text/plain',
                async: false,
        		success: function(resultData) { 
        			roles  = resultData;
        		},
        		error : function(jqXHR, textStatus, errorThrown) { 
        			roles  = "Failed to load roles.";
        		}
        	})  
            
            if(data[i].status == "0"){
                status = "<td style='color: green;'>Active</td>";
            }
            else{
                status = "<td style='color: red;'>Inactive</td>";
            }
            
            
        	if (id == data[i].oprId) { // gør man ikke kan slette sig selv.
        		$('#table_con').append('<tr id="' + data[i].oprId + '">' + status + '<td>' + data[i].oprId + '</td>' + '<td>' + data[i].oprFirstName + '</td>' + '<td>' + data[i].oprLastName + '</td>' + '<td>' + data[i].oprIni + '</td>' + '<td>' + data[i].oprEmail + '</td>' + '<td>' + data[i].oprCpr + '</td>' + '<td id="pass_td">' + roles  + '</td>' + '<td>' + '<p></p>' + '</td>' + '<td>' + '<button name="'+ data[i].oprId +'" class="edit_user">Edit</button>' + '</td>' + '</tr>');
        	} else {
        		$('#table_con').append('<tr id="' + data[i].oprId + '">' + status + '<td>' + data[i].oprId + '</td>' + '<td>' + data[i].oprFirstName + '</td>' + '<td>' + data[i].oprLastName + '</td>' + '<td>' + data[i].oprIni + '</td>' + '<td>' + data[i].oprEmail + '</td>' + '<td>' + data[i].oprCpr + '</td>' + '<td id="pass_td">' + roles  + '</td>' + '<td>' + '<button name="' + data[i].oprId + '" class="del_user">Delete</button>' + '</td>' + '<td>' + '<button name="'+ data[i].oprId +'" class="edit_user">Edit</button>' + '</td>' + '</tr>');
        	}
        });
        
        $.getScript( "assets/js/del_users.js", function( data, textStatus, jqxhr ) {
        	console.log( data ); // Data returned
        	console.log( textStatus ); // Success
        	console.log( jqxhr.status ); // 200
        	console.log( "js load was performed." );
        });
        
        $.getScript( "assets/js/edit_users.js", function( data, textStatus, jqxhr ) {
        	console.log( data ); // Data returned
        	console.log( textStatus ); // Success
        	console.log( jqxhr.status ); // 200
        	console.log( "js load was performed." );
        });
        
        console.log('tabel data load done');
    }); 
} 

// view display
function viewToHTML(data) {
	$('#table_con').empty();
	$.each(data, function (index, item) {
		var eachrow = "<tr>";
	    eachrow.append("<td>" + item[1] + "</td>");             
        
	    for (i = 1; i < item.length; i++) { 
	    	if(item[i + 1] != null) {
	    		eachrow.append("<td>" + item[i] + "</td>");
	    	}
	    }
	                 
	    eachrow.append("</tr>");
	    $('#table_con').append(eachrow);
	});
}


// load den user logget ind samt dens roller.
function loadLoginUser(id) {
	$.getJSON('api/opr/' + id, function(data) {
		$('#label_oprName').html(data.oprFirstName).fadeIn(200);
		userName = data.oprFirstName;
		id = data.oprId;
	
		console.log('User ' + data.oprId + ' name:' + data.oprFirstName);
        
		jQuery.ajax({
			url: "api/opr/getOprRoleList/" + id,
			type: "GET",
			contentType: 'text/plain',
			success: function(resultData) {
				roles = resultData;
				$('#oprRoles').html(resultData).fadeIn(1000); // skriver roller på label.
				if (roles.indexOf("Admin") == -1) { // gem menu punkter som kun skal kunne bruges af admin
					$('#user_but').hide();
				}
			},	
			error : function(jqXHR, textStatus, errorThrown) {
				
			},
			timeout: 120000,
		});
		console.log('user load done.')
	}
)};
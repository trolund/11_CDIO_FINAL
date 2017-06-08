var id = null;
var userName = null;
var roles = null;
var user = null;

// Alt der skal køres ved opstart af application!
$(document).ready(function(){
	$('#loadingGif').hide();
    $('#AddUser_Box').hide();
    
    $('#login').keydown(function(e) {
    	if (e.keyCode == 13) {
    		login();
    	}
    });  
});

// LogOut button menu  
$(document).ready(function() {
	$("#logOut_but").click(function() {
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
        login();
    } 
)});

function login() {  
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
            $('#msg').html('Connection to server failed.');
            $('#loadingGif').hide(200);
            console.log('Failed to log in - ' + data)
		}
	}
)};

// menu js
$(document).ready(function() {
     
// Menu mobile button 
	$("#menu_but").click(function() {
		$("#sidepanel ul").toggleClass("on");
		$("#sidepanel").toggleClass("on_sidepanel");
		$("#menu_but").toggleClass("rot");
	});
    
}); 

$(document).ready(function() {
// User button menu    
    $("#user_but").click(function() {
		$("#content_box").load('add_user.html');
		$('#AddUser_Box').hide();
        $('#editUser_Box').hide();
		loadUsers();
	});
}); 

$(document).ready(function() {
// view button menu     
    $("#view_but").click(function() {       
		$("#content_box").load('view.html');
		
		$.getScript( "assets/js/view.js", function( data, textStatus, jqxhr ) {
        	console.log( textStatus ); // Success
        	console.log( jqxhr.status ); // 200
        	console.log( "view.js was loaded." );
        }); 
	});
}); 
    
$(document).ready(function() {
// Menu pb button 
	$("#prodbatch_but").click(function() {
        $("#content_box").load('pb.html');
		loadpb(false);
	});    
}); 


// Hent liste af users og oversæt dem til tabel 
function loadUsers() {
	var inActiveCount = 0;
    var ActiveCount = 0;
    var totCount = 0;
    
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>Status</td><td>Id</td><td>First Name</td><td>Last Name</td><td>Initials</td><td>E-mail</td><td>Cpr</td><td>Roles</td><td>Delete</td><td>Edit</td></tr>');
    
//     $.ajaxSetup({
//        async: false
//        });
    
    $.getJSON('api/View/VUserTableList', function(data) {
    	console.log('Users loaded.');
	    
        var status;

        $.each(data, function(i, item) { 
            
            if (data[i].status == "0") {
                status = "<td style='color: green;'>Active</td>";
                ActiveCount++;
            } else {
                status = "<td style='color: red;'>Inactive</td>";
                inActiveCount++;
            }
            
        	if (id == data[i].oprId) { // gør man ikke kan slette sig selv.
        		$('#table_con').append('<tr id="' + data[i].oprId + '">' + status + '<td>' + data[i].oprId + '</td>' + '<td>' + data[i].oprFirstName + '</td>' + '<td>' + data[i].oprLastName + '</td>' + '<td>' + data[i].oprIni + '</td>' + '<td>' + data[i].oprEmail + '</td>' + '<td>' + data[i].oprCpr + '</td>' + '<td id="pass_td">' + data[i].oprRoles  + '</td>' + '<td>' + '<p></p>' + '</td>' + '<td>' + '<button data="'+ data[i].oprId +'" class="edit_user">Edit</button>' + '</td>' + '</tr>');
        	} else { 
        		$('#table_con').append('<tr id="' + data[i].oprId + '">' + status + '<td>' + data[i].oprId + '</td>' + '<td>' + data[i].oprFirstName + '</td>' + '<td>' + data[i].oprLastName + '</td>' + '<td>' + data[i].oprIni + '</td>' + '<td>' + data[i].oprEmail + '</td>' + '<td>' + data[i].oprCpr + '</td>' + '<td id="pass_td">' + data[i].oprRoles  + '</td>' + '<td>' + '<button name="' + data[i].oprId + '" class="del_user">Delete</button>' + '</td>' + '<td>' + '<button name="'+ data[i].oprId +'" class="edit_user">Edit</button>' + '</td>' + '</tr>');
        	}
        }); 
        
        $('#inActiveCount').html(inActiveCount).fadeIn(200);
        $('#activeCount').html(ActiveCount).fadeIn(200);
        totCount = inActiveCount + ActiveCount;
        $('#totCount').html(totCount).fadeIn(200);
        console.log('Total amuont of users: ' + totCount + ' Inactive: ' + inActiveCount + ' Active:' + ActiveCount);        
        console.log('tabel data load done'); 
        
        $.getScript( "assets/js/userlist_dy.js", function( data, textStatus, jqxhr ) {
        	console.log( textStatus ); // Success
        	console.log( jqxhr.status ); // 200
        	console.log( "userlist_dy.js was loaded." );
        }); 

    })
};
              
// load den user logget ind samt dens roller. 
function loadLoginUser(id) {
	$.getJSON('api/opr/' + id, function(data) {
        user = data;
        userName = data.oprFirstName;
		id = data.oprId;
        
		$('#label_oprName').html(data.oprFirstName).fadeIn(200);
        
		jQuery.ajax({
			url: "api/opr/getOprRoleList/" + id,
			type: "GET",
			contentType: 'text/plain',
			success: function(resultData) {
				roles = resultData;
				$('#oprRoles').html(resultData).fadeIn(1000); // skriver roller på label.
				Roletjek();
			},	
			error : function(jqXHR, textStatus, errorThrown) {
				
			},
			timeout: 120000,
		});
        console.log('User ' + data.oprId + ' name:' + data.oprFirstName);
	}
)};

function Roletjek(){ // tjekker hvad der skal vises i web UI
	if (roles.indexOf("Admin") == -1) { // gem menu punkter som kun skal kunne bruges af admin
		$('#user_but').hide();
	}
}

// nyt med inter

function loadpb(bool){
    
	$('#table_con').empty();
	
    $('#table_con').append("<tr><td>Status</td><td>Id</td><td>Item status</td><td>Recept Id</td><td>Delete</td><td>Edit</td></tr>");
    
    $.getJSON('api/pb/List', function(data) {
         
	    var status;
        
	    $.each(data, function(i, item) {
            
            var itemStatus = "<select class='selinput' name='itemStatus' id='itemStatus_val_" + data[i].pbId + "'><option value='0'>Ikke påbegyndt</option><option value='1'>Under produktion</option><option value='2'>Afsluttet</option></select>"
            
	    	if (data[i].status == "0") {
	    		status = "<td value='0' id='status_" + data[i].pbId + "' style='color: green;'>Active</td>";
	    	} else {
	    		status = "<td value='1' id='status_" + data[i].pbId + "' style='color: red;'>Inactive</td>";
	    	}
            
            if(bool && data[i].status == "1") {
                
            } else {
            	$('#table_con').append('<tr name="' + data[i].pbId + '" id="row">' + status + '<td><input id="pbId_' + data[i].pbId + '" type="text" value="' + data[i].pbId + '"></td>' + '<td>' + itemStatus + '</td>' + '<td><input id="receptId_' + data[i].pbId + '" type="text" value="' + data[i].receptId + '"></td>' + '<td><input class="checkbox" type="checkbox" name="' + data[i].pbId + '" name="del"></td>' + '<td><button class="edit_pb" name="' + data[i].pbId + '">Edit</button></td>' + '</tr>');
            }
            
            $('#itemStatus_val_' + data[i].pbId).val(data[i].itemStatus);
       }); 
        
        $.getScript( "assets/js/pb_dy.js", function( data, textStatus, jqxhr ) {
        	console.log( textStatus ); // Success
        	console.log( jqxhr.status ); // 200
        	console.log( "pb_dy.js was loaded." );
        }); 
    
    })
    tjekEditLock();
};
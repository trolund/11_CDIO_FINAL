var id = null;
var userName = null;
var rols = null;

var authHeader = "Bearer " + localStorage.getItem("jwt");

// alt der skal køres ved opstart af application!
$(document).ready(function(){
    $('#loadingGif').hide();
    $('#AddUser_Box').hide();
});


// logout button menu  
$(document).ready(function() {
  $("#logOut_but").click(function() {    
      var id = null;
      var user = null;
      var rols = null;
      $('#msg').html('');
      $('#login_Bg').show(1000);  
      $('#oprName').html('Username').delay(1000);
      $('#oprRoles').html('Rolle, rolle').delay(1000);
      $('#content_box').html('').delay(1000); 
     // window.location.reload(); måske skal den bruges....
});
  });

//post login data
$(document).ready(function() {
  $("#login_but").click(function() {
	  
	  id = $('#login_oprId').val();
      
      $('#login_Bg').show();
      $('#loadingGif').show(200);
      
      
      var Postdata = $('#login').serializeJSON();    
      
      jQuery.ajax({
		url : "api/opr/verify", 
		data : Postdata,
		contentType: "application/json",
		method: 'POST',
		success : function(data){
            if(data == 'Logged in successfully.'){
                $('#msg').css('color','green');
                $('#msg').html(data);
                $('#login_Bg').hide(1000);     
                $('#login_Bg').hide(200);
                $('#loadingGif').hide(200);
     
                loadLoginUser(id);
                rols.search("Admin");
            }
            else{
                $('#msg').css('color','red');
                $('#msg').html(data);
                $('#login_Bg').show();
                $('#loadingGif').hide(200);
                console.log('Failed to logged in - ' + data)
            }
		},
		error: function(jqXHR, text, error){  
                $('#msg').css('color','red');
                $('#msg').html('Server fail');
                $('#loadingGif').hide(200);
			//    alert(jqXHR.status + text + error);
                console.log('Failed to logged in - ' + data)
		}}

)} )} );

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
    $("#content_box").load('adduser.html');
      $('#AddUser_Box').hide();
    loadUsers();
});
  });

// hent liste af users og oversæt dem til tabel
function loadUsers(){
    $('#table_con').empty();                         
    $('#table_con').append('<tr><td>Id</td><td>Name</td><td>Initials</td><td>Cpr</td><td>Password</td><td>Delete</td><td>Edit</td></tr>');
    
    $.getJSON('api/opr/getOprList', function(data) {
	    console.log('Users loaded');
	
        $.each(data, function(i, item) {
        	if(id == data[i].oprId){ // gør man ikke kan slette sig selv.
        	$('#table_con').append('<tr id="' + data[i].oprId + '">' + '<td>' + data[i].oprId + '</td>' + '<td>' + data[i].oprName + '</td>' + '<td>' + data[i].oprIni + '</td>' + '<td>' + data[i].oprCpr + '</td>' + '<td id="pass_td">' + data[i].oprPassword + '</td>' + '<td>' + '<p>user logged in</p>' + '</td>' + '<td>' + '<button name="'+ data[i].oprId +'" class="edit_User">Edit</button>' + '</td>' + '</tr>');
        	}
        	else{
            $('#table_con').append('<tr id="' + data[i].oprId + '">' + '<td>' + data[i].oprId + '</td>' + '<td>' + data[i].oprName + '</td>' + '<td>' + data[i].oprIni + '</td>' + '<td>' + data[i].oprCpr + '</td>' + '<td id="pass_td">' + data[i].oprPassword + '</td>' + '<td>' + '<button name="' + data[i].oprId + '" class="del_User">Delete</button>' + '</td>' + '<td>' + '<button name="'+ data[i].oprId +'" class="edit_User">Edit</button>' + '</td>' + '</tr>');
        	}
        	});
        
        $.getScript( "assets/js/del_Users.js", function( data, textStatus, jqxhr ) {
  console.log( data ); // Data returned
  console.log( textStatus ); // Success
  console.log( jqxhr.status ); // 200
  console.log( "js Load was performed." );
});
        
        $.getScript( "assets/js/edit_Users.js", function( data, textStatus, jqxhr ) {
  console.log( data ); // Data returned
  console.log( textStatus ); // Success
  console.log( jqxhr.status ); // 200
  console.log( "js Load was performed." );
});
        
        console.log('tabel data done');
}); 
} 

// view display
function viewToHTML(data){
	$('#table_con').empty();
	$.each(data, function (index, item) {
	     var eachrow = "<tr>";
	     eachrow.append("<td>" + item[1] + "</td>");             
        
for (i = 1; i < item.length; i++) { 
    if(item[i+1] != null){
   eachrow.append("<td>" + item[i] + "</td>");
}
}
	                 eachrow.append("</tr>");
	     $('#table_con').append(eachrow);
	});
	
}


// load den user logget ind samt dens roller.
function loadLoginUser(id){

$.getJSON('api/opr/' + id, function(data) {
    
        $('#label_oprName').html(data.oprName).fadeIn(200);
        userName = data.oprName;
        id = data.oprId;
	    console.log('User ' + data.oprId + ' name:' + data.oprName);
        
        jQuery.ajax({
	  url: "api/opr/getOprRoleList/" + id,
	  type: "GET",
	  contentType: 'text/plain',
	  success: function(resultData) {
          rols = resultData;
	    $('#oprRoles').html(resultData).fadeIn(1000); // skriver roller på label.
        if(rols.indexOf("Admin") !== -1){ // gem menu punkter som kun skal kunne bruges af admin
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
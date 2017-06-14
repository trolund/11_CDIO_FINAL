var id = null;
var userName = null;
var roles = null;
var user = null;
// recept options.
var options;
var matOptions;

var popupTæller = 0;

// Alt der skal køres ved opstart af application!
$(document).ready(function() {
	$('#loadingGif').hide();
	$('#AddUser_Box').hide();
    $('#myPopup').hide();

	// hide menu punkter
	$('#user_but').hide();
	$('#startWeight').hide();
	$('#matbatch_but').hide();
	$('#prodbatch_but').hide();
	$('#mat_but').hide();
	$('#receipt_but').hide();
	$('#startWeight').hide();
    $('#StrongManagement').hide();
    $('#StrongSettigns').hide();

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
	});
});

// Post login data
$(document).ready(function() {
	$("#login_but").click(function() {
		login();
	})
});

function login() {
	id = $('#login_oprId').val();
	$('#login_Bg').show();
	$('#loadingGif').show(200);

	var postData = $('#login').serializeJSON();

	jQuery.ajax({
		url : "api/opr/verify",
		data : postData,
		contentType : "application/json",
		method : 'POST',
		success : function(data) {
			if (data == 'Logged in successfully.') {
				$('#msg').css('color', 'green');
				$('#msg').html(data);
				$('#login_Bg').hide(1000);
				$('#login_Bg').hide(200);
				$('#loadingGif').hide(200);
				loadLoginUser(id);
			} else {
				$('#msg').css('color', 'red');
				$('#msg').html(data);
				$('#login_Bg').show();
				$('#loadingGif').hide(200);
				console.log('Failed to log in - ' + data)
			}
		},
		error : function(jqXHR, text, error) {
			$('#msg').css('color', 'red');
			$('#msg').html('Connection to server failed.');
			$('#loadingGif').hide(200);
			console.log('Failed to log in - ' + data)
		}
	})
};
    
   

function showPopup(msg, type){
    
    $('body').append('<span style="display:none;" class="popup" id="myPopup_' + popupTæller + '">Popup</span>');
    
    if(type){
        $('#myPopup_' + popupTæller).css("background-color", "green");
    }
    else{
        $('#myPopup_' + popupTæller).css("background-color", "red");
    }
    $('#myPopup_' + popupTæller).html(msg);
    $('#myPopup_' + popupTæller).show(200);
    $('#myPopup_' + popupTæller).delay(1000).hide(200);
    popupTæller++;
}

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

		$.getScript("assets/js/view.js", function(data, textStatus, jqxhr) {
			console.log(textStatus); // Success
			console.log(jqxhr.status); // 200
			console.log("view.js was loaded.");
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

$(document).ready(function() {
	// Menu recept button
	$("#receipt_but").click(function() {
		$("#content_box").load('recept.html');
		loadrecept(false);
	});
});

$(document).ready(function() {
	// Menu material batch button
	$("#matbatch_but").click(function() {
		$("#content_box").load('mb.html');
		loadmb(false);
	});
});

$(document).ready(function() {
	// Menu material button
	$("#mat_but").click(function() {
		$("#content_box").load('material.html');
		loadmaterial(false);
	});
});

$(document).ready(function() {
	// Menu Setings --> User button
	$("#settings_but").click(function() {
		$("#content_box").empty();
		$("#content_box").load('user_settings.html');
	});
});

$(document).ready(function() {
	// Menu Setings --> User button
	$("#startWeight").click(function() {

		jQuery.ajax({
			url : "api/weight/start",
			type : "GET",
			contentType : 'text/plain',
			success : function(resultData) {
				$("#startWeight").addClass('.active');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#startWeight").removeClass('.active');
			},
			timeout : 120000,
		});

	});
});

// Hent liste af users og oversæt dem til tabel
function loadUsers() {
	var inActiveCount = 0;
	var ActiveCount = 0;
	var totCount = 0;

	$('#table_con').empty();
	$('#table_con')
			.append(
					'<tr><td>Status</td><td>Id</td><td>First Name</td><td>Last Name</td><td>Initials</td><td>E-mail</td><td>Cpr</td><td>Roles</td><td>Delete</td><td>Edit</td></tr>');

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

			var oprRoles;
			if (data[i].oprRoles === null) {
				oprRoles = "None.";
			} else {
				oprRoles = data[i].oprRoles;
			}

			if (id == data[i].oprId) { // gør man ikke kan slette sig selv.
				$('#table_con').append(
						'<tr id="' + data[i].oprId + '">' + status + '<td>'
								+ data[i].oprId + '</td>' + '<td>'
								+ data[i].oprFirstName + '</td>' + '<td>'
								+ data[i].oprLastName + '</td>' + '<td>'
								+ data[i].oprIni + '</td>' + '<td>'
								+ data[i].oprEmail + '</td>' + '<td>'
								+ data[i].oprCpr + '</td>'
								+ '<td id="pass_td">' + oprRoles + '</td>'
								+ '<td>' + '<p></p>' + '</td>' + '<td>'
								+ '<button name="' + data[i].oprId
								+ '" class="edit_user">Edit</button>' + '</td>'
								+ '</tr>');
			} else {
				$('#table_con').append(
						'<tr id="' + data[i].oprId + '">' + status + '<td>'
								+ data[i].oprId + '</td>' + '<td>'
								+ data[i].oprFirstName + '</td>' + '<td>'
								+ data[i].oprLastName + '</td>' + '<td>'
								+ data[i].oprIni + '</td>' + '<td>'
								+ data[i].oprEmail + '</td>' + '<td>'
								+ data[i].oprCpr + '</td>'
								+ '<td id="pass_td">' + oprRoles + '</td>'
								+ '<td>' + '<button name="' + data[i].oprId
								+ '" class="del_user">Delete</button>'
								+ '</td>' + '<td>' + '<button name="'
								+ data[i].oprId
								+ '" class="edit_user">Edit</button>' + '</td>'
								+ '</tr>');
			}
		});

		$('#inActiveCount').html(inActiveCount).fadeIn(200);
		$('#activeCount').html(ActiveCount).fadeIn(200);
		totCount = inActiveCount + ActiveCount;
		$('#totCount').html(totCount).fadeIn(200);
		console.log('Total amuont of users: ' + totCount + ' Inactive: '
				+ inActiveCount + ' Active:' + ActiveCount);
		console.log('tabel data load done');

		$.getScript("assets/js/userlist_dy.js", function(data, textStatus,
				jqxhr) {
			console.log(textStatus); // Success
			console.log(jqxhr.status); // 200
			console.log("userlist_dy.js was loaded.");
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
			url : "api/opr/getOprRoleList/" + id,
			type : "GET",
			contentType : 'text/plain',
			success : function(resultData) {
				roles = resultData;
				$('#oprRoles').html(resultData).fadeIn(1000); // skriver
																// roller på
																// label.
				$('#content_box').html('Welcome ' + user.oprFirstName + ' ' + user.oprLastName + '. A navigation menu can be found at the left hand side.');
				Roletjek();

			},
			error : function(jqXHR, textStatus, errorThrown) {

			},
			timeout : 120000,
		});
		console.log('User ' + data.oprId + ' name:' + data.oprFirstName);
	})
};

function Roletjek() { // tjekker hvad der skal vises i web UI

	// hide menu punkter
	$('#user_but').hide();
	$('#startWeight').hide();
	$('#matbatch_but').hide();
	$('#prodbatch_but').hide();
	$('#mat_but').hide();
	$('#receipt_but').hide();
	$('#startWeight').hide();

	if (roles.indexOf("Admin") != -1) { // Admin: Admin skal kunne se alt.
        $('#StrongSettigns').show(200);
        $('#StrongManagement').show(200);
		$('#user_but').show(200);
		$('#startWeight').show(200);
		$('#matbatch_but').show(200);
		$('#prodbatch_but').show(200);
		$('#mat_but').show(200);
		$('#receipt_but').show(200);

	}
	if (roles.indexOf("Farmaceut") != -1) { // Farmaceut: Skal kunne se Material
		$('#StrongSettigns').show(200);		
        $('#StrongManagement').show(200);// og Recepter.
		$('#matbatch_but').show(200);
		$('#prodbatch_but').show(200);
		$('#mat_but').show(200);
		$('#receipt_but').show(200);
	}
	if (roles.indexOf("Værkfører") != -1) { // Værkfører: Productbatches og
		$('#StrongSettigns').show(200);									// material batches.
		$('#matbatch_but').show(200);
        $('#StrongManagement').show(200);
		$('#prodbatch_but').show(200);
		$('#startWeight').show(200);
	}
	if (roles.indexOf("Laborant") != -1) { // Laborant: Intet/ eller måske det
		$('#StrongSettigns').show(200);									// samme som værkføren.

	}

}

function loadpb(bool) {
	$('#table_con').empty();
	$('#table_con')
			.append(
					"<tr><td>Status</td><td>Id</td><td>Item Status</td><td>Receipt Id</td><td>Delete</td><td>Edit</td></tr>");
	options = null;

	$
			.getJSON(
					'api/pb/List',
					function(data) {
						var receptOptions = "";
						options = "";

						$
								.each(
										data,
										function(i, item) {
											if (receptOptions
													.indexOf(data[i].receptId) == -1) {
												receptOptions += data[i].receptId
														+ ",";
												options += "<option value='"
														+ data[i].receptId
														+ "'>"
														+ data[i].receptId
														+ "</option>";
											}
										});

						console.log(options);

						$
								.each(
										data,
										function(i, item) {
											var itemStatus = "<select class='selinput' name='itemStatus' id='itemStatus_val_"
													+ data[i].pbId
													+ "'><option value='0'>Ikke påbegyndt</option><option value='1'>Under produktion</option><option value='2'>Afsluttet</option></select>"

											var status = "<td><select class='statusinput selinput' name='Status' id='Status_val_"
													+ data[i].pbId
													+ "'><option value='0'>Active</option><option value='1'>Inactive</option></select></td>"

											if (bool && data[i].status == "1") {

											} else {
												$('#table_con')
														.append(
																'<tr name="'
																		+ data[i].pbId
																		+ '" id="row">'
																		+ status
																		+ '<td id="pbId_'
																		+ data[i].pbId
																		+ '">'
																		+ data[i].pbId
																		+ '</td>'
																		+ '<td>'
																		+ itemStatus
																		+ '</td>'
																		+ '<td><select class="selinput" id="receptId_'
																		+ data[i].pbId
																		+ '">'
																		+ options
																		+ '<select></td>'
																		+ '<td><input class="checkbox" type="checkbox" name="'
																		+ data[i].pbId
																		+ '" name="del"></td>'
																		+ '<td><button class="edit_pb" name="'
																		+ data[i].pbId
																		+ '">Edit</button></td>'
																		+ '</tr>');
											}

											$('#Status_val_' + data[i].pbId)
													.val(data[i].status);
											$('#receptId_' + data[i].pbId).val(
													data[i].receptId);
											$('#itemStatus_val_' + data[i].pbId)
													.val(data[i].itemStatus);
										});

						$.getScript("assets/js/pb_dy.js", function(data,
								textStatus, jqxhr) {
							console.log(textStatus); // Success
							console.log(jqxhr.status); // 200
							console.log("pb_dy.js was loaded.");
							tjekEditLock();
						});
					})
};

function loadrecept(bool) {
	$('#table_con').empty();
	$('#table_con')
			.append(
					"<tr><td>Status</td><td>Id</td><td>Name</td><td>Delete</td><td>Edit</td></tr>");
	options = null;

	$
			.getJSON(
					'api/recept/List',
					function(data) {
						var receptOptions = "";

						$
								.each(
										data,
										function(i, item) {
											if (receptOptions
													.indexOf(data[i].receptId) == -1) {
												receptOptions += data[i].receptId
														+ ",";
												options += "<option value='"
														+ data[i].receptId
														+ "'>"
														+ data[i].receptId
														+ "</option>";
											}
										});

						console.log(options);

						$
								.each(
										data,
										function(i, item) {
											console.log(data);

											var status = "<td><select class='statusinput selinput' name='Status' id='Status_val_"
													+ data[i].receptId
													+ "'><option value='0'>Active</option><option value='1'>Inactive</option></select></td>"

											if (bool && data[i].status == "1") {

											} else {
												$('#table_con')
														.append(
																'<tr name="'
																		+ data[i].receptId
																		+ '" id="row">'
																		+ status
																		+ '<td id="receptId_'
																		+ data[i].receptId
																		+ '">'
																		+ data[i].receptId
																		+ '</td>'
																		+ '<td><input id="receptNavn_'
																		+ data[i].receptId
																		+ '" value="'
																		+ data[i].receptName
																		+ '">'
																		+ '</td>'
																		+ '<td><input class="checkbox" type="checkbox" name="'
																		+ data[i].receptId
																		+ '" name="del"></td>'
																		+ '<td><button class="edit_recept" name="'
																		+ data[i].receptId
																		+ '">Edit</button></td>'
																		+ '</tr>');
											}

											$('#Status_val_' + data[i].pbId)
													.val(data[i].status);
										});

						$.getScript("assets/js/recept_dy.js", function(data,
								textStatus, jqxhr) {
							console.log(textStatus); // Success
							console.log(jqxhr.status); // 200
							console.log("recept_dy.js was loaded.");
							tjekEditLock();
						});
					})
};

function loadmaterial(bool) {
	$('#table_con').empty();
	$('#table_con')
			.append(
					"<tr><td>Status</td><td>Id</td><td>Name</td><td>Supplier</td><td>Delete</td><td>Edit</td></tr>");

	$
			.getJSON(
					'api/material/List',
					function(data) {
						console.log(data);

						$
								.each(
										data,
										function(i, item) {
											console.log(data);
											var status = "<td><select class='statusinput selinput' name='Status' id='Status_val_"
													+ data[i].raavareId
													+ "'><option value='0'>Active</option><option value='1'>Inactive</option></select></td>"

											if (bool && data[i].status == "1") {

											} else {
												$('#table_con')
														.append(
																'<tr name="'
																		+ data[i].raavareId
																		+ '" id="row">'
																		+ status
																		+ '<td id="raavareId_'
																		+ data[i].raavareId
																		+ '">'
																		+ data[i].raavareId
																		+ '</td>'
																		+ '<td><input id="materialName_'
																		+ data[i].raavareId
																		+ '" value="'
																		+ data[i].raavareName
																		+ '">'
																		+ '</td>'
																		+ '<td><input id="Supplier_'
																		+ data[i].raavareId
																		+ '" value="'
																		+ data[i].supplier
																		+ '">'
																		+ '</td>'
																		+ '<td><input class="checkbox" type="checkbox" name="'
																		+ data[i].raavareId
																		+ '" name="del"></td>'
																		+ '<td><button class="edit_material" name="'
																		+ data[i].raavareId
																		+ '">Edit</button></td>'
																		+ '</tr>');
											}

											$(
													'#Status_val_'
															+ data[i].raavareId)
													.val(data[i].status);
										});

						$.getScript("assets/js/material_dy.js", function(data,
								textStatus, jqxhr) {
							console.log(textStatus); // Success
							console.log(jqxhr.status); // 200
							console.log("recept_dy.js was loaded.");
							tjekEditLock();
						});
					})
};

function loadmb(bool) {
	$('#table_con').empty();
	$('#table_con')
			.append(
					"<tr><td>Status</td><td>Id</td><td>Material Id</td><td>Amount</td><td>Delete</td><td>Edit</td></tr>");

	$
			.getJSON(
					'api/material/List',
					function(data) {
						var materialOptions = "";
						matOptions = "";

						$.each(data,
								function(i, item) {
									if (materialOptions
											.indexOf(data[i].raavareId) == -1) {
										materialOptions += data[i].receptId
												+ ",";
										matOptions += "<option value='"
												+ data[i].raavareId + "'>"
												+ data[i].raavareId
												+ "</option>";
									}
								});

						console.log(options);

						$
								.getJSON(
										'api/mb/List',
										function(data) {
											$
													.each(
															data,
															function(i, item) {

																console
																		.log(data);

																var status = "<td><select class='statusinput selinput' name='Status' id='Status_val_"
																		+ data[i].rbId
																		+ "'><option value='0'>Active</option><option value='1'>Inactive</option></select></td>"

																if (bool
																		&& data[i].status == "1") {

																} else {
																	$(
																			'#table_con')
																			.append(
																					'<tr name="'
																							+ data[i].rbId
																							+ '" id="row">'
																							+ status
																							+ '<td id="rbId_'
																							+ data[i].rbId
																							+ '">'
																							+ data[i].rbId
																							+ '</td>'
																							+ '<td id="raavareId_'
																							+ data[i].rbId
																							+ '"><select class="selinput" id="Mat_val_'
																							+ data[i].rbId
																							+ '">'
																							+ matOptions
																							+ '</select></td>'
																							+ '<td><input id="maengde_'
																							+ data[i].rbId
																							+ '" value="'
																							+ data[i].amount
																							+ '">'
																							+ '</td>'
																							+ '<td><input class="checkbox" type="checkbox" name="'
																							+ data[i].rbId
																							+ '" name="del"></td>'
																							+ '<td><button class="edit_materialb" name="'
																							+ data[i].rbId
																							+ '">Edit</button></td>'
																							+ '</tr>');
																}

																$(
																		'#Status_val_'
																				+ data[i].rbId)
																		.val(
																				data[i].status);
																$(
																		'#Mat_val_'
																				+ data[i].rbId)
																		.val(
																				data[i].raavareId);
															});

											$
													.getScript(
															"assets/js/mb_dy.js",
															function(data,
																	textStatus,
																	jqxhr) {
																console
																		.log(textStatus); // Success
																console
																		.log(jqxhr.status); // 200
																console
																		.log("mb_dy.js was loaded.");
																tjekEditLock();
															});
										})
					})
};

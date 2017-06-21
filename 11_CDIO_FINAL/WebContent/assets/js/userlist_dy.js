// Delete users 
$(document).ready(function () {
    $('.del_user').click(function () {

        var id = $(this).prop('name');
        console.log('Try to delete user with id: ' + id);

        jQuery.ajax({
            url: "api/opr/deleteOpr",
            type: "POST",
            data: id,
            contentType: "application/json",

            success: function (resultData) {
                if (resultData == "true") {
                    console.log('User successfully deleted.' + 'return:' + resultData);
                    $('tr#' + id + ' td:first-child').html("<td style='color: red;'>Inactive</td>").fadeIn(200);

                } else {
                    console.log('User NOT deleted.' + 'return:' + resultData);
                    $('#' + id).css('background-color', 'yellow').fadeIn(400);
                }
            },

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Id [" + id + "] does not exist.");
            },

            timeout: 120000,
        });
    });
});

// Edit user button
$(document).ready(function () {
    $(".edit_user").click(function () {

        var id = $(this).prop('name');

        $('#AdminRoleEdit').prop('checked', false);
        $('#FarRoleEdit').prop('checked', false);
        $('#VeakRoleEdit').prop('checked', false);
        $('#LabRoleEdit').prop('checked', false);

        console.log("click:" + id);

        $.getJSON('api/opr/RoleList/' + id, function (roleData) {

            console.log('her:');
            console.log(roleData);

            $.each(roleData, function (i, item) {



                if (roleData[i].roleName == "Admin") {
                    $('#AdminRoleEdit').prop('checked', true);
                } else if (roleData[i].roleName == "Farmaceut") {
                    $('#FarRoleEdit').prop('checked', true);
                } else if (roleData[i].roleName == "Værkfører") {
                    $('#VeakRoleEdit').prop('checked', true);
                } else if (roleData[i].roleName == "Laborant") {
                    $('#LabRoleEdit').prop('checked', true);
                }
            });
        }, function () {

            console.log("fejl");

        });

        $.getJSON('api/opr/' + id, function (data) {
            console.log('User with id: ' + id + " loaded for edit");
            console.log(data);

            $('#statusEdit').val(data.status);
            $('#oprIdEdit').val(data.oprId);
            $('#oprFirstNameEdit').val(data.oprFirstName);
            $('#oprLastNameEdit').val(data.oprLastName);
            $('#oprIniEdit').val(data.oprIni);
            $('#oprEmailEdit').val(data.oprEmail);
            $('#oprCprEdit').val(data.oprCpr);

            console.log(data + "added to form")
        });

        $("#editUser_Box").show(400);
    });
});
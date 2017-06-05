$(document).ready(function(){
// toggle pb button 
$('#checkbox_val').click(function(){
    console.log("check");
    $('input').attr("readonly", $('#checkbox_val').is(":checked")); 
});

});


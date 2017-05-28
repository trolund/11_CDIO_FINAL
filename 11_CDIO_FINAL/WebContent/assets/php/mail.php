<?php
$to      = $_POST["mail"];
$subject = 'New password';
$message = 'hej, din adminitrator har givet dig et nyt kode ord. dine login oplysninger er nu: id:' $_POST["id"] + $_POST["newPassword"]  ;
$headers = 'From: Admin@DTUgruppe11.com' . "\r\n" .
    'Reply-To: webmaster@example.com' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();

mail($to, $subject, $message, $headers);
?> 
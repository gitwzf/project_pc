<?php
//测试邮件群发功能
require("phpmailer/class.phpmailer.php");

$mail = new phpmailer();
$mail->From     = "list@example.com";
$mail->FromName = "List manager";
$mail->Host     = "smtp1.example.com;smtp2.example.com";
$mail->Mailer   = "smtp";

$id = $_GET["id"];
@MYSQL_CONNECT("localhost","root","password");
@mysql_select_db("phpbook_ch16");
$query ="SELECT full_name, email,photo FROM `users` WHERE id=$id";
$result = mysql_query($query);

while ($row = mysql_fetch_array ($result))
{
	//HTML body
	$body  = "Hello <font size=\"4\">" . $row["full_name"] . "</font>, <p>";
	$body .= "<i>Your</i> personal photograph to this message.<p>";
	$body .= "Sincerely, <br>";
	$body .= "phpmailer List manager";

	//Plain text body (for mail clients that cannot read HTML)
	$text_body  = "Hello " . $row["full_name"] . ", \n\n";
	$text_body .= "Your personal photograph to this message.\n\n";
	$text_body .= "Sincerely, \n";
	$text_body .= "phpmailer List manager";
	//设置邮件body,这儿为HTML各式
	$mail->Body    = $body;
	//设置文本注意altbody
	$mail->AltBody = $text_body;
	//增加邮件发送地址
	$mail->AddAddress($row["email"], $row["full_name"]);
	//增加附件
	$mail->AddStringAttachment($row["photo"], "YourPhoto.jpg");

	if(!$mail->Send())
	echo "There has been a mail error sending to " . $row["email"] . "<br>";

	// Clear all addresses and attachments for next loop
	$mail->ClearAddresses();
	$mail->ClearAttachments();
}
?>
<?php
$conn = mysql_connect("222.35.32.123:3311","root","insp###ion_oak")
		or die("Could not connect: " . mysql_error());
mysql_select_db("mysql",$conn)
		or die ('Can\'t use database : ' . mysql_error());
?>
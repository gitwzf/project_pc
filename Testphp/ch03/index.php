<?php
	$id = $_GET["id"];
	$op = $_GET["op"];
	$id = isset($id) ? $id : "0";
	$op = isset($op) ? $op : "noting";
	echo "��ѡ���ID=".$id."��op=".$op;
?>
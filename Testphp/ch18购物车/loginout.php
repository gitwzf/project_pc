<?php
session_start();
unset($_SESSION['userid']);
header("Location:usershow.php");
exit();
?>
<?php
$username="s2439564";
$password="s2439564";
$database="d2439564";
$link=mysqli_connect("127.0.0.1",$username,$password,$database);

$order_id=$_REQUEST["ORDER_ID"];
$order_status= $_REQUEST["ORDER_STATUS"];
$sql_update="UPDATE ORDER_TBL SET ORDER_STATUS= '$order_status' where ORDER_ID='$order_id'";
$link->query($sql_update);
?>


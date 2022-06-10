<?php
$username="s2439564";
$password="s2439564";
$database="d2439564";
$resID=$_REQUEST["resID"];
$link=mysqli_connect("127.0.0.1",$username,$password,$database);
$sql_function="SELECT getRestaurant('$resID')";
$r=mysqli_query($link,$sql_function);
$row=$r->fetch_assoc();
mysqli_close($link);
echo json_Encode($row);
?>

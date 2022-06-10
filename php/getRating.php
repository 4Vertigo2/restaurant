<?php
$username="s2439564";
$password="s2439564";
$database="d2439564";
$link= mysqli_connect("127.0.0.1",$username,$password,$database);
$sql_rating="CALL CALCULATEAVERAGE()";

$output=array();
$r=mysqli_query($link,$sql_rating);
while($row=$r->fetch_assoc()){
$output[]=  $row;
}

mysqli_close($link);
echo json_encode($output);
?>


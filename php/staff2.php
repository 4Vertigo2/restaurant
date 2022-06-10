<?php
$username ="s2439564";
$password="s2439564";
$database="d2439564";
$link=mysqli_connect("127.0.0.1", $username, $password, $database);

$STAFF= $_REQUEST["LOGIN_ID"];
$output=array();

if($r= mysqli_query($link, "SELECT * FROM STAFF_TBL WHERE LOGIN_ID='$STAFF'")){
while($row=$r->fetch_assoc()){
$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>

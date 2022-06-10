<?php
$username="s2439564";
$password="s2439564";
$database="d2439564";
$link = mysqli_connect("127.0.0.1", $username, $password,$database);

$output =array();

if($result = mysqli_query($link,"Select * from CUSTOMER_TBL")){
while ($row=$result->fetch_assoc()){
$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>


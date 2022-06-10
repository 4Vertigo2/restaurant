<?php
$username="s2439564";
$password="s2439564";
$database="d2439564";
$link =mysqli_connect("127.0.0.1",$username,$password,$database);
$staff_id=$_REQUEST["STAFF_ID"];
$customer_id=$_REQUEST["CUSTOMER_ID"];

$sql_insert ="INSERT INTO ORDER_TBL(STAFF_ID,CUSTOMER_ID) VALUES('$staff_id','$customer_id')";

if ($link-> query($sql_insert)===TRUE){

echo "O1";
}else{
echo "O0";
}
?>

 

<?php
$username = "s2439564";
$password = "s2439564";
$database = "d2439564";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$user_id = $_REQUEST["user_id"];
$user_first_name = $_REQUEST["user_first_name"];
$user_surname = $_REQUEST["user_surname"];
$user_phone_number = $_REQUEST["user_phone_number"];

$user_login_id = $_REQUEST["user_login_id"];
$user_login_username = $_REQUEST["user_login_username"];
$user_login_password = $_REQUEST["user_login_password"];


if($link -> connect_error){
    die("Connection failed: ".$conn->connect_error);
}

$sql_update_login = "Update LOGIN_TBL set LOGIN_USERNAME = '".$user_login_username."', LOGIN_PASSWORD='".$user_login_password."' WHERE LOGIN_ID = '".$user_login_id."'";

if($link->query($sql_update_login)=== TRUE){
    echo "L1";
}else{
    echo "L0";
}


$sql_update_staff = "Update STAFF_TBL set STAFF_FIRST_NAME='".$user_first_name."', STAFF_SURNAME='".$user_surname."', STAFF_PHONE_NUMBER='".$user_phone_number."' where STAFF_ID ='".$user_id."'";
if($link->query($sql_update_staff) === TRUE){
    echo "S1";
}
else{
    echo "S0";
}
	

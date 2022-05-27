<?php
$username = "s2439564";
$password = "s2439564";
$database = "d2439564";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$user_first_name = $_REQUEST["user_first_name"];
$user_surname = $_REQUEST["user_surname"];
$user_phone_number = $_REQUEST["user_phone_number"];
$user_login_username = $_REQUEST["user_login_username"];
$user_login_password = $_REQUEST["user_login_password"];
$user_login_staff = $_REQUEST["user_login_staff"];
$user_restaurant_id = $_REQUEST["user_restaurant_id"];

if($link -> connect_error){
    die("Connection failed: ".$conn->connect_error);
}

$sql_insert_login = "Insert into LOGIN_TBL values('".$user_login_username."','".$user_login_password."','".$user_login_staff."')";

if($link->query($sql_insert_login)=== TRUE){
    echo "login inserted successfully";
}else{
    echo "error login insert";
}

$sql_insert_staff = "Insert into STAFF_TBL values('".$user_first_name."','".$user_surname."','".$user_phone_number."','".$user_restaurant_id."',SELECT LAST_INSERT_ID())";

if($link->query($sql_insert_staff) === TRUE){
    echo "Staff insert successful";
}
else{
    echo "staff insert failed";
}
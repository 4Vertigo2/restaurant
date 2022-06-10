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

if($link -> connect_error){
    die("Connection failed: ".$conn->connect_error);
}

$sql_insert_login = "Insert into LOGIN_TBL(LOGIN_USERNAME,LOGIN_PASSWORD,LOGIN_STAFF) values('".$user_login_username."','".$user_login_password."','".$user_login_staff."')";

if($link->query($sql_insert_login)=== TRUE){
    echo "L1";
}else{
    echo "L0";
}

$sql_insert_customer = "Insert into CUSTOMER_TBL(CUSTOMER_FIRST_NAME, CUSTOMER_SURNAME, CUSTOMER_PHONE_NUMBER,LOGIN_ID) values('".$user_first_name."','".$user_surname."','".$user_phone_number."',(SELECT MAX(LOGIN_ID) FROM LOGIN_TBL))";

if($link->query($sql_insert_customer) === TRUE){
    echo "S1";
}
else{
    echo "S0";
}

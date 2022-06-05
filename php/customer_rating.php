<?php
$username = "s2439564";
$password = "s2439564";
$database = "d2439564";
$link = mysqli_connect("127.0.0.1",$username, $password, $database);

/*get the rating value from the textview and add it to the order*/
$order_rating = $_REQUEST["rating"];
$order_id = $_REQUEST["id"];

if($link -> connect_error){
    die("Connection failed: ".$conn->connect_error);
}

$sql_update_rating = "update ORDER_TBL
                                set ORDER_RATING = ".$order_rating." 
                                where ORDER_ID = ".$order_id."";

if($link->query($sql_update_rating)=== TRUE){
    echo "Rating added";
}
else{
    echo "Rating failed";
}
?>

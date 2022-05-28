<?php
$username = "s2439564";
$password = "s2439564";
$database = "d2439564";
$link = mysqli_connect("127.0.0.1",$username, $password, $database);
$output = array();
/*get all orders from the customer_id, then display the ones that 
are either pending or ready, or completed*/
$customer_id = $_REQUEST["customer_id"];
$status = $_REQUEST["status"];

if($r = mysqli_query($link, " Select ORDER_ID, ORDER_STATUS, RESTAURANT_NAME, ORDER_TIME 
                            from ((ORDER_TBL 
                            inner join STAFF_TBL on ORDER_TBL.STAFF_ID = STAFF_TBL.STAFF_ID) 
                            inner join RESTAURANT_TBL on STAFF_TBL.RESTAURANT_ID = RESTAURANT_TBL.RESTAURANT_ID) 
                            where CUSTOMER_ID = ".$customer_id." and ORDER_STATUS = ".$status."")){
	while($row=$r->fetch_assoc()){
		$output[]=$row;
	}
}
mysqli_close($link);
echo json_encode($output);
?>
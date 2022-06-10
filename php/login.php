<?php   
$username = "s2439564";
$password = "s2439564";
$database = "d2439564";
$link = mysqli_connect("127.0.0.1",$username, $password, $database);
$output = array();
/*check if the username exists, if it does give us all the users data;*/
$username = $_REQUEST["username"];
$password = $_REQUEST["password"];

if($result = mysqli_query($link, "Select * from LOGIN_TBL where LOGIN_USERNAME='".$username."'and LOGIN_PASSWORD='".$password."'")){
	while($row=$result->fetch_assoc()){
		$output[]=($row);
	}
	if($output[0]["LOGIN_STAFF"] == TRUE){
		if($result = mysqli_query($link, "SELECT * FROM STAFF_TBL WHERE LOGIN_ID='".$output[0]["LOGIN_ID"]."'")){
			while($row=$result->fetch_assoc()){
				$output[]=$row;	
			}

		}

	}
	else{
		if($result = mysqli_query($link, "SELECT * FROM CUSTOMER_TBL WHERE LOGIN_ID= '".$output[0]["LOGIN_ID"]."'")){
			while($row=$result->fetch_assoc()){
				$output[]=$row;	
			}

		}
	}
}
else{
}
mysqli_close($link);
echo json_encode($output);
?>

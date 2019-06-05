<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$password = ($_POST['password']);
$phone = $_POST['phone'];
$name = $_POST['name'];
$homename = $_POST['homename'];
$location = $_POST['location'];
$position = $_POST['position'];

if (strlen($email) > 0){
    $sqlinsert = "INSERT INTO USER(EMAIL,PASSWORD,PHONE,NAME,HOMENAME,LOCATION,POSITION) VALUES ('$email','$password','$phone','$name','$homename','$location','$position')";
    if ($conn->query($sqlinsert) === TRUE){
       echo "success";
    }else {
        echo "failed";
    }
}else{
    echo "nodata";
}

?>
<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$oldpassword = ($_POST['opassword']);
$newpassword = ($_POST['npassword']);
$phone = $_POST['phone'];
$name = $_POST['name'];
$location = $_POST['newloc'];




$sqlcheck = "SELECT * FROM USER WHERE PHONE = '$phone' AND password = '$oldpassword'";
$result = $conn->query($sqlcheck);
if ($result->num_rows > 0) {
 $sqlupdate = "UPDATE USER SET EMAIL = '$email', PASSWORD = '$newpassword', NAME = '$name',LOCATION = '$location'  WHERE PHONE = '$phone' AND PASSWORD = '$oldpassword'";
  if ($conn->query($sqlupdate) === TRUE){
        echo 'success';
  }else{
      echo 'failed';
  }   
}else{
    echo "failed";
}

 
?>




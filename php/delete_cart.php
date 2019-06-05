<?php
error_reporting(0);
include_once("dbconnect.php");
$userid = $_POST['userid'];
$donationitemid = $_POST['donationitemid'];
    $sqldelete = "DELETE FROM CART WHERE USER_ID = '$userid' AND DONATION_ITEM_ID='$donationitemid'";
    if ($conn->query($sqldelete) === TRUE){
       echo "success";
    }else {
        echo "failed";
    }
?>
<?php
error_reporting(0);
include_once("dbconnect.php");
$donationitemid = $_POST['donationitemid'];
$userid = $_POST['userid'];
$quantity = $_POST['quantity'];
$itemprice = $_POST['itemprice'];
$itemname = $_POST['itemname'];
$itemcondition = $_POST['itemcondition'];
$status = "not complete";

$sqlsel = "SELECT * FROM DONATION_ITEMS WHERE DONATION_ITEM_ID = '$donationitemid'";
$result = $conn->query($sqlsel);
if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        $qavail = $row["QUANTITY"];
    }
    $bal = $qavail - $quantity; 
}

$sqlsel = "SELECT * FROM DONATION_ITEMS WHERE DONATION_ITEM_ID = '$donationitemid'";
$result = $conn->query($sqlsel);
if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        $qavail = $row["QUANTITY"];
    }
    $bal = $qavail - $quantity; 
    if ($bal>0){
        $sqlupdate = "UPDATE DONATION_ITEMS SET QUANTITY = '$bal' WHERE DONATION_ITEM_ID = '$donationitemid'";
        $conn->query($sqlupdate);
        $sqlinsert = "INSERT INTO CART(DONATION_ITEM_ID,USER_ID,QUANTITY,ITEM_PRICE,ITEM_NAME,ITEM_CONDITION,STATUS) VALUES 
		('$donationitemid','$userid','$quantity','$itemprice','$itemname','$itemcondition',$status')";
        if ($conn->query($sqlinsert) === TRUE){
            echo $bal."success";
        }else {
            echo "failed";
        }
    }
}else{
    echo "failed";
}



?>
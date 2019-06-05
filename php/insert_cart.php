<?php
error_reporting(0);
include_once("dbconnect.php");
$donationitemid = $_POST['donationitemid'];
$userid = $_POST['userid'];
$quantity = $_POST['quantity'];
$itemprice = $_POST['itemprice'];
$itemcondition = $_POST['itemcondition'];
$itemname = $_POST['itemname'];
$donationid = $_POST['donationid'];
$status = "not paid";
    
$sqlsel = "SELECT * FROM DONATION_ITEMS WHERE DONATION_ITEM_ID = '$donationitemid'";
$result = $conn->query($sqlsel);
if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        $qavail = $row["QUANTITY"];
    }
    $bal = $qavail - $quantity; 
}
if ($bal>0){
    $sqlgetid = "SELECT * FROM CART WHERE USER_ID = '$userid' AND STATUS='not paid'";
    $result = $conn->query($sqlgetid);
    $sqlupdate = "UPDATE DONATION_ITEMS SET QUANTITY = '$bal' WHERE DONATION_ITEM_ID = '$donationitemid'";
        $conn->query($sqlupdate);
        
if ($result->num_rows > 0) {
    while ($row = $result ->fetch_assoc()){
        $orderid = $row["ORDERID"];
    }
     $sqlinsert = "INSERT INTO CART(DONATION_ITEM_ID,USER_ID,QUANTITY,ITEM_PRICE,ITEM_NAME,ITEM_CONDITION,STATUS,DONATION_ID,ORDERID) VALUES 
	 ('$donationitemid','$userid','$quantity','$itemprice','$itemname','$itemcondition','$status','$donationid','$orderid')";
     
    if ($conn->query($sqlinsert) === TRUE){
       echo "success";
    }
}else{
    $orderid = generateRandomString();
   $sqlinsert = "INSERT INTO CART(DONATION_ITEM_ID,USER_ID,QUANTITY,ITEM_PRICE,ITEM_NAME,ITEM_CONDITION,STATUS,DONATION_ID,ORDERID) VALUES 
   ('$donationitemid','$userid','$quantity','$itemprice','$itemname','$itemcondition','$status','$donationid','$orderid')";
    if ($conn->query($sqlinsert) === TRUE){
       echo "success";
    }
}
}



function generateRandomString($length = 7) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return date('dmY')."-".$randomString;
}

?>
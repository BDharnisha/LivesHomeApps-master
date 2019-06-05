<?php
error_reporting(0);
include_once("dbconnect.php");
$userid = $_POST['userid'];

$sql = "SELECT * FROM CART WHERE USER_ID = '$userid' AND STATUS = 'not paid'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    $response["cart"] = array();
    while ($row = $result ->fetch_assoc()){
        $cartlist = array();
        $cartlist[donationitemid] = $row["DONATION_ITEM_ID"];
        $cartlist[itemname] = $row["ITEM_NAME"];
        $cartlist[itemprice] = $row["ITEM_PRICE"];
        $cartlist[itemcondition] = $row["ITEM_CONDITION"];
        $cartlist[quantity] = $row["QUANTITY"];
        $cartlist[status] = $row["STATUS"];
        $cartlist[donationid] = $row["DONATION_ID"];
        $cartlist[orderid] = $row["ORDERID"];
        array_push($response["cart"], $cartlist);
    }
    echo json_encode($response);
}else{
    echo "nodata";
}
?>
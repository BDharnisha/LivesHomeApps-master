<?php
error_reporting(0);
include_once("dbconnect.php");
$donationid = $_POST['donationid'];

$sql = "SELECT * FROM DONATION_ITEMS WHERE DONATION_ID = '$donationid'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    $response["donationitem"] = array();
    while ($row = $result ->fetch_assoc()){
        $donationitemlist = array();
        $donationitemlist[donationitemid] = $row["DONATION_ITEM_ID"];
        $donationitemlist[itemname] = $row["ITEM_NAME"];
        $donationitemlist[itemcondition] = $row["ITEM_CONDITION"];
		$donationitemlist[itemprice] = $row["ITEM_PRICE"];
        $donationitemlist[quantity] = $row["QUANTITY"];
        array_push($response["donationitem"], $donationitemlist);
    }
    echo json_encode($response);
}else{
    echo "nodata";
}
?>


<?php
error_reporting(0);
include_once("dbconnect.php");
$itemcategory = $_POST['itemcategory'];
if (strcasecmp($itemcategory, "All") == 0){
    $sql = "SELECT * FROM DONATION"; 
}else{
    $sql = "SELECT * FROM DONATION WHERE ITEM_CATEGORY = '$itemcategory'";
}
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    $response["don"] = array();
    while ($row = $result ->fetch_assoc()){
        $donlist = array();
        $donlist[donationid] = $row["DONATION_ID"];
        $donlist[donationname] = $row["DONATION_NAME"];
        $donlist[donorname] = $row["DONOR_NAME"];
        $donlist[donorphone] = $row["DONOR_PHONE_NO"];
        $donlist[donorlocation] = $row["DONOR_LOCATION"];
		$donlist[itemcategory] = $row["ITEM_CATEGORY"];
        array_push($response["don"], $donlist);
    }
    echo json_encode($response);
}else{
    echo "nodata";
}
?>
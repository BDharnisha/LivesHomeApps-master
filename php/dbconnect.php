<?php
$servername = "localhost";
$username   = "id9044355_sha";
$password   = "sukunadevi";
$dbname     = "id9044355_liveshome";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
<?php
// The world's smallest shopping center.
// Name: Freddie Pike
// Student Number: 201252723
// ShopPage2.php
session_start();
echo"<html>".
        "<head><h1 style=\"text-align:center\">Checking Out</h1></head>".
        "<body>";

// These 2 variables grab the select box values and multiply them by the book 
// price to get the total price of the order.
$webAppPriceAmount = $_SESSION[webAppQuantity] * 95;
$jsPriceAmount = $_SESSION[jsQuantity] * 135;

// A table that contains information about the item name, number of books 
// ordered, the price of the books and the total price of user's order.
echo '<table align=center border=1 >';
    echo '<tr>';
        echo "<th align=center><p>Item</p></th>";
        echo "<th><p>Quantity</p></th>";
        echo "<th><p>Price</p></th>";
        echo "<th><p>Amount</p></th>";
    echo '</tr>';
    echo '<tr>';
        echo "<td><p>Web Applications</p></td>";
        echo "<td align=right>$_SESSION[webAppQuantity]</td>";
        echo "<td align=right><p>$95</p></td>";
        echo "<td><p>$$webAppPriceAmount</p></td>";
    echo '</tr>';   
    echo '<tr>';
        echo "<td><p>JavaScript</p></td>";
        echo "<td align=right>$_SESSION[jsQuantity]</td>";
        echo "<td align=right><p>$135</p></td>";
        echo "<td><p>$$jsPriceAmount</p></td>";
    echo '</tr>'; 
echo '</table>';

echo "</body>"
. "</html>";
?>
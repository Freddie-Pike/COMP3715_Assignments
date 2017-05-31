<?php
// The world's smallest shopping center.
// Name: Freddie Pike
// Student Number: 201252723
// ShopPage1.php
session_start();
echo"<html>". 
    "<head><h1 style=\"text-align:center\">A Small Shopping Site</h1></head>".
        "<body>";

// This while loop will set each $_SESSION variable to their correct values 
// instead of setting both variables at the same time.
while (list($key,$value) = each($_GET)) {
    if ($key == "webBookNumber")
    {
        $_SESSION['webAppQuantity'] = $_GET[webBookNumber];
    }
    else if ($key == "jsBookNumber")
    {
        $_SESSION['jsQuantity'] = $_GET[jsBookNumber];
    }
}

echo '<table align=center >';
    // Each row contains the book description, the select box and a submit button.
    // Each row element is contained in a form tag that will allow the $_GET to 
    // grab each select value separately. 
    echo '<tr>';
        echo'<form action="ShopPage1.php" method="get">';
        echo "<td><p><b>Web Applications</b><br> "
                . "This Book provides an<br>"
                . "in-depth examination of<br>"
                . "the basic concepts and<br>"
                . "general principles<br>"
                . "associated with Web<br>"
                . "application<br>"
                . "development. The price<br>"
                . "is $95<br></p></td>";
        echo '<td valign=top><select name="webBookNumber">'.
                '<option>0</option>'.
                '<option>1</option>'.
                '<option>2</option>'.
                '<option>3</option>'.
                '<option>4</option>'.
                '<option>5</option>'.
             '</select></td>';
        echo '<td valign=top><input type="submit" value="Add to Cart"></td>';
        echo '</form>';
    echo '</tr>';
    echo '<tr>';
        echo'<form action="ShopPage1.php" method="get">';
        echo "<td><p><b>Javascript</b><br> "
                . "This is both an<br>"
                . "example-driven<br>"
                . "programmer's guide<br>"
                . "and a keep-on-<br>"
                . "your-desk reference<br>"
                . "with new chapters that<br>"
                . "explain everything you<br>"
                . "need to know to get the<br>"
                . "most out of JavaScript<br>"
                . "The price is $130<br></p></td>";
        echo '<td valign=top><select name="jsBookNumber">'.
            '<option>0</option>'.
            '<option>1</option>'.
            '<option>2</option>'.
            '<option>3</option>'.
            '<option>4</option>'.
            '<option>5</option>'.
        '</select><br></td>';
        echo '<td valign=top><input type="submit" value="Add to Cart"></td>';
        echo '</form>';
    echo '</tr>';   
echo '</table>';
echo '</form>';

// This hyperlink text allows the user to check out and go to the next page. 
echo '<p style="text-align:center"><a href="ShopPage2.php">Check out</a></p>';
echo "</body>"
. "</html>";
?>
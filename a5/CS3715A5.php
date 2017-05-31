<?php 
session_start();
$dir = "../CS3715_A5_V1/";
$dirFiles = scandir($dir); // Grabbing the directory that the will contain the text files for comments.
$DatabaseFileArray = [$dirFiles[3], $dirFiles[5], $dirFiles[7], $dirFiles[10]];
?>
<html>
    <head>
        <title>CS3715 A5</title>
    </head>
    <body onload="xhttpGrabTextFile(images[0])">
        <script>
            var images = ['A.png', 'B.png', 'C.png', 'D.png'];
            var counter = 0;
            var xhttp = new XMLHttpRequest();
            var xhttpGetNextImage = new XMLHttpRequest();
            
            function GetPrevImage() {
                counter--;
                if (counter < 0) { counter = 3; }
                var divTag = document.getElementById("divTest");
                divTag.removeChild(divTag.childNodes[0]);
                var imageA = document.createElement("IMG");
                imageA.setAttribute("src", images[counter]);
                divTag.appendChild(imageA);
                
                xhttpGrabTextFile(images[counter]);
            }
            
            function GetNextImage() {
                counter++;
                if (counter > 3) { counter = 0; }
                var divTag = document.getElementById("divTest");
                divTag.removeChild(divTag.childNodes[0]);
                var imageA = document.createElement("IMG");
                imageA.setAttribute("src", images[counter]);
                divTag.appendChild(imageA);
                // xhttpChangeNextImage(A.png);, This doesn't work.
                xhttpGrabTextFile(images[counter]);
            }
            
            function AddComment() {
                var divTag = document.getElementById("CommentDiv");
                var textArea = document.getElementById("CommentArea");
                var para = document.createElement("p");
                var textNode = document.createTextNode(textArea.value);
                para.appendChild(textNode);
                divTag.appendChild(para);
                
            }
            
            function xhttpGrabTextFile(text){
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("CommentDiv").innerHTML = xhttp.responseText;
                    }
                };
                xhttp.open("GET", text+"().txt", true);
                xhttp.send();
            }
            
            function xhttpChangeNextImage(text) {
                xhttpGetNextImage.onreadystatechange = function() {
                    if (xhttpGetNextImage.readyState == 4 && xhttpGetNextImage.status == 200) {
                        counter++;
                        if (counter > 3) { counter = 0; }
                        var divTag = document.getElementById("divTest");
                        divTag.removeChild(divTag.childNodes[0]);
                        var imageA = document.createElement("IMG");
                        imageA.setAttribute("src", images[counter]);
                        divTag.appendChild(imageA);
                    }
                };
                xhttpGetNextImage.open("GET", text+"().txt", true);
                xhttpGetNextImage.send();
            }
        </script>
        <div id="divTest"><img src="A.png" alt ="name"></div><br>
        <button onclick="GetPrevImage()">Previous</button>
        <button onclick="GetNextImage()">Next</button><br>
        <textarea wrap="soft" rows="10" cols="40" name="text" 
                  id="CommentArea"></textarea><br>
        <button onclick="AddComment()" id = "CommentButton">AddComment</button>
        <div id="CommentDiv"><p>Text goes here</p></div>
    </body>
</html>
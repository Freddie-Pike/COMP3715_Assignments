import java.io.*;
import java.net.*;

public class LoginSystem 
{

	/**
	 * If no connection found, send not found message to client. 
	 * @param out
	 */
    public static void sendNotFound( BufferedWriter out ) 
    {
        PrintWriter pr = new PrintWriter( out );
        pr.println("HTTP/1.0 404 Not Found");
        pr.println("Connection: close");
        pr.println();
        pr.flush();
    }

    /**
     * Show the user Name and password field. will be the first webpage they see
     * unless something goes wrong.
     * @param out
     * @param loginName
     * @param loginPassword
     */
    public static void sendReply(BufferedWriter out, String loginName, String loginPassword ) 
    {
        PrintWriter pr = new PrintWriter( out );
        pr.println("HTTP/1.0 200 OK");
        pr.println("Connection: close");
        pr.println("Content-Type: text/html");
        pr.println("Set-Cookie: "+loginName+" "+loginPassword+"; path=/; domain=localhost"); 
        pr.println();
        pr.println("<html>");
        pr.println("<head>");
        pr.println("<title>text and password field</title>");
        pr.println("</head>");
        pr.println("<body>");
        pr.println("<p style=\"text-align:center\"> Enter your user name and password <p>");
        pr.println("<form action=\"response-post\" method=\"get\">"
        		+ "		<table align=\"center\" valign=\"center\">"
        		+ "			<tr>"	
        		+ "				<td> Name:  </td>"
        		+ "				<td> <input name=login><br> </td>"
        		+ "			</tr>"
        		+ "			<tr>"
        		+ "				<td> Password: </td>"
        		+ "				<td> <input type=password name=password><br></td>"
        		+ "			</tr>"
        		+ "			<tr>"
        		+ "				<td> <input type=submit value=Submit> </td>"
        		+ "			</tr>"
        		+ "		</table>"
        		+ "</form>");
        pr.println("</body>");
        pr.println("</html>");
        pr.flush();
    }
    
    /**
     * If the user enters an username and password that is not in textfile, send 
     * them an notice saying that they made a mistake.
     * @param out
     * @param loginName
     * @param loginPassword
     */
    public static void sendInvalidLoginReply(BufferedWriter out, String loginName, String loginPassword ) 
    {
        PrintWriter pr = new PrintWriter( out );
        pr.println("HTTP/1.0 200 OK");
        pr.println("Connection: close");
        pr.println("Content-Type: text/html");
        pr.println("Set-Cookie: "+loginName+" "+loginPassword+"; path=/; domain=localhost");
        pr.println();
        pr.println("<html>");
        pr.println("<head>");
        pr.println("<title>text and password field</title>");
        pr.println("</head>");
        pr.println("<body>");
        pr.println("<p style=\"text-align:center\"> Incorrect user name or password <p>");
        pr.println("<p style=\"text-align:center\"> Please enter again <p>");
        
        pr.println("<form action=\"response-post\" method=\"get\">"
        		+ "		<table align=\"center\" valign=\"center\">"
        		+ "			<tr>"	
        		+ "				<td> Name:  </td>"
        		+ "				<td> <input name=login><br> </td>"
        		+ "			</tr>"
        		+ "			<tr>"
        		+ "				<td> Password: </td>"
        		+ "				<td> <input type=password name=password><br></td>"
        		+ "			</tr>"
        		+ "			<tr>"
        		+ "				<td> <input type=submit value=Submit> </td>"
        		+ "			</tr>"
        		+ "		</table>"
        		+ "</form>");
        pr.println("</body>");
        pr.println("</html>");
        pr.flush();
    }
    
    /**
     * If the user entered an username and password that is in the textfile, show them a webpage
     * telling them that the login was successful. 
     * @param out
     * @param loginName
     * @param loginPassword
     */
    public static void sendSuccessfulLoginReply(BufferedWriter out, String loginName, String loginPassword) 
    {
        PrintWriter pr = new PrintWriter( out );
        pr.println("HTTP/1.0 200 OK");
        pr.println("Connection: close");
        pr.println("Content-Type: text/html");
        pr.println("Set-Cookie: "+loginName+" "+loginPassword+"; path=/; domain=localhost");  
        pr.println();
        pr.println("<html>");
        pr.println("<head>");
        pr.println("<title>text and password field</title>");
        pr.println("</head>");
        pr.println("<body>");
        pr.println("<p style=\"text-align:center\"> Login Successful! <p>");
        
        pr.println("<form action=\"response-post\" method=\"get\">"
        		+ "		<table align=\"center\" valign=\"center\">"
        		+ "			<tr>"	
        		+ "				<td> Name:  </td>"
        		+ "				<td> <input name=login><br> </td>"
        		+ "			</tr>"
        		+ "			<tr>"
        		+ "				<td> Password: </td>"
        		+ "				<td> <input type=password name=password><br></td>"
        		+ "			</tr>"
        		+ "			<tr>"
        		+ "				<td> <input type=submit value=Submit> </td>"
        		+ "			</tr>"
        		+ "		</table>"
        		+ "</form>");
        pr.println("</body>");
        pr.println("</html>");
        pr.flush();
    }
    
    

    public static void main( String[] args ) 
    {
        try 
        {
            ServerSocket listen = new ServerSocket( 5000 );
            System.out.println ("Accepting HTTP request from port: " + listen.getLocalPort());
            while ( true ) 
            {
                Socket sock = listen.accept();
                BufferedReader rd = new BufferedReader(
                    new InputStreamReader( sock.getInputStream() ));
                BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter( sock.getOutputStream() ));
                // Get request
                String line = rd.readLine();
                if ( line == null ) {
                    sock.close();
                    continue;
                }
                String[] words = line.split("\\s+");
                System.out.println(line );
                
                // Because we are using the get method, we grab the username
                // and password from the url. 
                String loginInformationLine = words[1];
                System.out.println(loginInformationLine);
                String[] splitLine = loginInformationLine.split("\\?");
                String[] loginInfo = {"",""};
                // If statement that will used after the user enters in login information. 
                if (splitLine.length > 1)
                {
                	System.out.println(splitLine[1]);
                	loginInfo = splitLine[1].split("\\&");
                	System.out.println("loginUsername: " + loginInfo[0]
                		+ "\nloginPassword: " + loginInfo[1]);		
                }
                		
                // Creating a do while loop to grab the cookie for 
                // the username and password. 
                String cookieUsername = "";
                String cookiePassword = "";
                do 
                {
                    line = rd.readLine();
                    if ( line == null ) break;
                    if (line.length() > 0) { 
                        String[]  fields = line.split("\\s*:\\s*"); 
                        if (fields[0].equals("Cookie")) { 
                            String[] cfields = fields[1].split("=");
                            // if statement to check if the user has actually an username and password.
                            if (cfields.length > 2)
                            {
                            	cookiePassword = cfields[2];
                            	cookieUsername = cfields[1].split(" ")[0];
                            }
                            else
                            {
                            	// If the user hasn't entered in any data, make cookies into default values. 
                            	cookiePassword = ""; 
                            	cookieUsername = "";
                            }
                        } 
                    } 
                    System.out.println( line );
                } 
                while ( line.length() > 0 );
                
            	// The name of the file to open.
                String fileName = "input.txt";

                // This will reference one line at a time.
                String textFileLine = null;

                // Creating the array that will contain the username and password.
                String[] textFileLoginInfo = {"",""};
                String textFileUsername = "";
                String textFilePassword = "";
                try {
                    // Creating FileReader and BufferedReader to read in text file.
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    
                    try
                    {
                    	// loop until there are no more lines.
                    	while((textFileLine = bufferedReader.readLine()) != null) 
                    	{
                    		// Split the line so into an array of 2 elements(an username and 
                    		// password). 
                    		textFileLoginInfo = textFileLine.split(" ");
                    		textFileUsername = "login=" + textFileLoginInfo[0];
                    		textFilePassword = "password=" + textFileLoginInfo[1];
                    		// If username and password are equal to the information the user
                    		// has entered then break with the correct textFile information.
                    		if (textFileUsername.equals(loginInfo[0]))
                    		{
                    			if (textFilePassword.equals(loginInfo[1]))
                    			{
                    				System.out.println("Login Success!");
                    				break;
                    			}
                    		}
                    	}
                    }
                    catch(IOException ex)
                    {
                    	System.out.println("IOException in reading text file");
                    }
                    // Close bufferedReader.
                    bufferedReader.close();
                }
                catch(FileNotFoundException ex) 
                {
                    System.out.println("Unable to open file '" + fileName + "'");
                }
                catch(IOException ex) 
                { 
                	System.out.println("Error reading file '" + fileName + "'");
                }
                
                // Format the cookie information before the if statements. 
                cookieUsername = "login=" + cookieUsername;
                cookiePassword = "password=" + cookiePassword;
                
                // Print out the value of the user entered in information, the textfile login information, and the cookie login information.
                System.out.println("Successful Login Test:\nTFU: " + textFileUsername + "\nCU: " + cookieUsername + "\nLIU: " + loginInfo[0]);
                System.out.println("Successful Password Test:\nTFP: " + textFilePassword + "\nCP: " + cookiePassword + "\nLIP: " + loginInfo[1]);
                
                if ( words[1].equals("/favicon.ico") ) 
                {
                    sendNotFound( bw );
                }
                // If user entered in the correct information, then show a webpage acknowledging that.
                else if (((loginInfo[0].equals(textFileUsername)) && (loginInfo[1].equals(textFilePassword))) )
                {
                	sendSuccessfulLoginReply(bw,loginInfo[0], loginInfo[1] );
                }
                // If cookie information is in the text file then tell the user that they are still logged in.
                else if (((cookieUsername.equals(textFileUsername)) && (cookiePassword.equals(textFilePassword))) )
                {
                	sendSuccessfulLoginReply(bw,loginInfo[0], loginInfo[1] );
                }
                // Send user default webpage upon first start up. 
                else if (words[1].equals("/"))
                {
                    sendReply( bw, loginInfo[0], loginInfo[1] );
                }
                // If user has entered in incorrect information, then show a webpage acknowledging that.
                else 
                {
                	sendInvalidLoginReply(bw, loginInfo[0], loginInfo[1] );
                }
                rd.close();
                bw.close();
                sock.close();
            }
       }
       catch( IOException e ) 
       {
           System.out.println("Error in creating webpage: " + e );
       }
    }
}
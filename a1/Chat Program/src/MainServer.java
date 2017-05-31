import java.io.*;
import java.net.*;

/**
 * A program for a server that will allow chat clients to connect to it
 * that is able to send messages to other clients as well as send error
 * messages.
 * @author Taylor Freddie Pike
 *
 */
public class MainServer 
{
    private Socket socket;
    ServerSocket server;
    
    /**
     * Starting the run method in order for other clients to connect to server.
     * @param sock = the socket
     */
    public MainServer(ServerSocket s) 
    {
    	server = s;
    	try
    	{
    		socket = server.accept();
    		StartChat();
    	}
    	catch (IOException ex)
		{
			System.out.println("IOException in MainServer, Report as follows\n" + ex);
		}
    }

    public void StartChat() 
    {
    		try 
    		{
    			System.out.println("Chat Client Accepted!: " + socket);
    	    	
    			// BufferedReaders to send and receive texts.
				BufferedReader rd = new BufferedReader
						(new InputStreamReader( socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter( socket.getOutputStream()));
				String line = "";
				
				// Send line back to all clients.
				while ( (line = rd.readLine()) != null)
				{
					bw.write( "User1 Writes: ");
					bw.flush();
					System.out.println( line );
				}
    		}
    		catch (IOException ex)
    		{
    			System.out.println("IOException in StartChat, Report as follows\n" + ex);
    		}
    	}
    
    // Main method creates ServerSocket and starts MainServer. 
    public static void main( String[] args ) 
    {
    	try 
		{
        	System.out.println("Welcome to ChatterChat!\nWAIT");
        	ServerSocket server = new ServerSocket( 0 );
        	System.out.println("CLIENT_PORT " + server.getLocalPort());

	    	while ( true ) 
	    	{
                new MainServer(server);
	    	}
		}
    	catch (IOException ex)
		{
			System.out.println("IOException in main method, Report as follows\n" + ex);
		}
    }
}
import java.io.*;
import java.net.*;

/**
 * A program for a chat client that will connect to a chat server that is
 * able to send messages to other clients as well as send error messages.
 * @author Taylor Freddie Pike
 *
 */
public class ChatterClient 
{
	public static void main ( String[] args ) 
	{
		// If arguments not proper, then exit. 
		if ( args.length != 2 ) 
		{
			System.out.println("usage: java ChatterClient host port");
			System.exit(1);
		}
		int port = 0;
		String host = null;
		try 
		{
			// Get host and port from arguments.
			host = args[0];
			port = Integer.parseInt(args[1]);
			System.out.println("PEER_LOC " + host + ":" + port);
		}
		catch ( NumberFormatException e )
		{
			System.out.println("bad port number");
			System.exit(1);
		}
		try
		{
			// Determine the address of the server and connect to it.
			InetAddress server = InetAddress.getByName(host);
			Socket socket = new Socket( server, port );
			
			// Get the input Stream.
			InputStream in = socket.getInputStream();
						
			// Get the output Stream.
			OutputStream out = socket.getOutputStream();
						
			// Attach it to a print writer.
			PrintWriter wr = new PrintWriter( out, true );
			BufferedReader rr = 
					new BufferedReader(new InputStreamReader( in ));
						
			// Get an input Reader.
			BufferedReader rd =
					new BufferedReader( new InputStreamReader(System.in));
				
			// Read input
			String line = null;
			while ( (line = rd.readLine()) != null) 
			{
				wr.println( line );
				rr.readLine();
			}
			
			// Terminate the connection 
			socket.close();
		}
		catch (UnknownHostException e ) 
		{
			System.out.println("bad host name");
			System.exit(0);
		}
		catch (IOException ex)
		{
			System.out.println("IOException in 2nd try block in main method, "
					+ "Report as follows\n" + ex);
			System.exit(0);
		}
	}
}

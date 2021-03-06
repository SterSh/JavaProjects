package shaw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {



    public String readHTTP(String url) {
        try {
            // Convert the String url to a URL object which will allow a connection to be established
            URL urlObj = new URL(url);

            // Open a Connection to the remote site.  This will send the HTTP Get request
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            // Open an input stream to read the response.  I prefer the BufferedReader since it provides
            // the readLine function.
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // When concatenating strings read from the remote server, we get some performance improvement by
            // using the StringBuilder class.
            StringBuilder data = new StringBuilder();
            String line;

            // Keep reading from the remote server until we get a null which indicates the connection has
            // been closed.
            do {
                line = reader.readLine();  // Read the next line
                if (line != null) {
                    data.append(line); // Append will concatenate the new string just read into the StringBuilder
                }
            }
            while (line != null);

            // Convert the StringBuilder into a regular String for use by the calling function
            return data.toString();
        }
        catch (IOException ioe) {
            // Print out an error if something went wrong and return a null response string
            System.out.println("Error reading HTTP Response: "+ioe);
            return null;
        }
    }
}

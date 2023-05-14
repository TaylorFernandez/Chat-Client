package datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ExternalCommunicationManager{
    private String url;

    private static ExternalCommunicationManager singleton;
    private String data;
    private boolean sendData;

    private ExternalCommunicationManager() {
        this.sendData = false;
    }

    public static ExternalCommunicationManager getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new ExternalCommunicationManager();
        return null;
    }

    public synchronized void sendData(String data, String url) throws IOException {
        System.out.println("Sending Data!");
        this.url = url;
        this.data = data;
        this.sendData = true;


        URL serverUrl = new URL(url);

        // Open a connection to the server
        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set the request headers
        connection.setRequestProperty("Content-Type", "application/json");

        // Prepare the data to send
        String jsonData = data;
        System.out.println("JsonData: \n" + data);

        // Send the data
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonData.getBytes());
        outputStream.flush();

        // Get the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        System.out.println("Response: " + response.toString());

        // Close the connection
        connection.disconnect();
    }
}

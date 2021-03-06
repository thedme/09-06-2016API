package localhost.googlemaps.directions;

import java.io.*;
import java.net.*;


/**
 * @author Dovid Eskenazi
 * @since 2016-09-06
 *
 */
public class RestRequest {
	/**
	 * The URL of the API we want to connect to
	 * 
	 */
	
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
	/*
	 * the character set to use when encoding URL parameters
	 * 
	 */
	
	protected static String charset = "UTF-8";
	
	/*
	 * 
	 * API key used for making requests to API
	 */
	
	protected static String key = "AIzaSyDGP5PNMHqUms__GLT_Org_lRAPxe-qIx8";
	
	
	
	public static void main(String[] args) {
		
		try {
			
			// The origin or starting point for directions
			String origin = "Columbia MD";
			
			//The destination or end point for directions
			String destination = "Charlotte NC";
			
			//The return type of the response xml|json
			String returnType = "xml";
			
			// the return language of Hebrew()
			String language = "iw";
			
			//to make the travel mode not the default value;
			String mode = "transit";
			
			//to make the travel type to bus
			String mode_travel = "bus";
			
			String avoid = "highways";
			
			String units = "metric";
			
			String queryString = String.format("origin=%s&destination=%s&key=%s&language=%s&mode=%s&mode_travel=%s&avoid=%s&units=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(key, charset),
					URLEncoder.encode(language, charset),
					URLEncoder.encode(mode, charset),
					URLEncoder.encode(mode_travel, charset),
					URLEncoder.encode(avoid, charset),
					URLEncoder.encode(units, charset)
					);
			
			//creates a new URL out of the endpoint, terurnType and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			// if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code :" + connection.getResponseCode());
				
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			// loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() != null) {
				System.out.println(br.readLine());
			}
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
}
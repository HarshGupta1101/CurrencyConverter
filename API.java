package mini_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class API{
	
    // essential URL structure is built using constants 
	public static final String ACCESS_KEY = "1196854a85d9c4e8495f5a2815d00977";
	public static final String BASE_URL = "http://api.currencylayer.com/";
	public static final String ENDPOINT = "live";

	// this object is used for executing requests to the (REST) API
	static CloseableHttpClient httpClient = HttpClients.createDefault();
	static double conversion1,conversion2;
	static String formattedDate;
	static double finalexchangerate;
	public double sendLiveRequest(String from, String to){
		
		// The following line initializes the HttpGet Object with the URL in order to send a request
		HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
		
		try {
			CloseableHttpResponse response =  httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			
			// the following line converts the JSON Response to an equivalent Java Object
			JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
			// parsed JSON Objects are accessed according to the JSON resonse's hierarchy, output strings are built
			  Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000)); 
		      DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
			  formattedDate = dateFormat.format(timeStampDate);
			  conversion1=exchangeRates.getJSONObject("quotes").getDouble("USD"+from);
			  conversion2=exchangeRates.getJSONObject("quotes").getDouble("USD"+to);
			  finalexchangerate=conversion2/conversion1;
			response.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalexchangerate;
	}

    // sendLiveRequest() function is executed
	public static void main(String[] args) throws IOException{
		httpClient.close();
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
}
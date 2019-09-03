package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 * Servlet implementation class RestComposeHandler
 */
public class RestComposeHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RestComposeHandler() {
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject myJSONObject=null;
		PrintWriter out = response.getWriter();
		HttpClient client = HttpClientBuilder.create().build();
		String requestURL ="https://jsonplaceholder.typicode.com/todos/1";
		HttpGet Getrequest = new HttpGet(requestURL);
		HttpResponse Invokedresponse = client.execute(Getrequest);
		/*
		 * BufferedReader rd = new BufferedReader (new
		 * InputStreamReader(Invokedresponse.getEntity().getContent())); String line =
		 * ""; while ((line = rd.readLine()) != null) { System.out.println(line); }
		 */
		HttpEntity entity = Invokedresponse.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			String result = convertStreamToString(instream);
			System.out.println(result);
			 myJSONObject = new JSONObject(result);
			 out.println("UserID :"+myJSONObject.getInt("userId"));
			 out.println("Title : "+myJSONObject.getString("title"));
			 out.println("ID :" + myJSONObject.getInt("id"));
			 out.println("Completed :"+myJSONObject.getBoolean("completed"));
			 
			 
		  }
	}

	private String convertStreamToString(InputStream instream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	        	instream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

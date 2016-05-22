package main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class CRUDClient {
	
	private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUDAssignGradeBookService/gradebook";
	
    public CRUDClient() {
		super();
		ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
	}
	
    public ClientResponse viewGrade(String id, String itemname){
    	webResource = client.resource(BASE_URI).path("viewgrade");
    	ClientResponse response = webResource.path(id).path(itemname).get(ClientResponse.class);
    	return response;
    }
}

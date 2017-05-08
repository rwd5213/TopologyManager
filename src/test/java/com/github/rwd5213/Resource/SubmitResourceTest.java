package com.github.rwd5213.Resource;

import javax.ws.rs.core.Response;

import com.github.rwd5213.Utils.TopologyObjectCreate;
import com.github.rwd5213.manager.resource.SubmitResource;

public class SubmitResourceTest {

	private SubmitResource submitResource;
	
	public void testSubmitResource(){
		String payload = TopologyObjectCreate.getPayload1().toString();
		
		Response response = submitResource.createSubmission(payload);
		
	}
}

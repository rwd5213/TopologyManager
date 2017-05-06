package com.github.rwd5213.manager.resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rwd5213.manager.persistence.TopologyMetadata;
import com.github.rwd5213.manager.service.SubmitService;
import com.github.rwd5213.manager.utils.ManagerUtils;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/topology")
public class SubmitResource {
	private static final Logger logger = LoggerFactory.getLogger(SubmitResource.class);

	private SubmitService submitService = new SubmitService();
	
	@GET
	@Path("/test")
	public Response testService(){
		String output = "Service is working";
		return Response.ok().entity(output.toString()).build();
	}
	
	@POST
	@NotNull
	@Path("/submit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSubmission(String payload){
		
		//Payload checks
		if(StringUtils.isBlank(payload)){
			JSONObject jsonObject = new JSONObject();
			logger.error("Payload is empty");
			jsonObject.put("Message", "Failed: Cannot be empty or blank");
			return Response.serverError().entity(jsonObject.toString()).build();
		}
	
		JSONObject payloadObject = new JSONObject(); 
		try{
			payloadObject = new JSONObject(payload);
		}catch(JSONException e){
			JSONObject jsonObject = new JSONObject();
			logger.error("Payload is not valid JSON.");
			jsonObject.put("Error", "Payload is not valid JSON.");
			return Response.serverError().entity(jsonObject.toString()).build();
		}
		
		
		Map<String, Object> createTopologyMetadata = ManagerUtils.getFinalCleanedMetadata(payloadObject);
		
		if (createTopologyMetadata.containsKey("Response")){
			return (Response) createTopologyMetadata.get("Response");
		}
		
		
		if(!(createTopologyMetadata.containsKey(ManagerUtils.JAR)) || !(createTopologyMetadata.containsKey(ManagerUtils.CLASSPATH))){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ManagerUtils.SUCCESS, ManagerUtils.FALSE);
			jsonObject.put(ManagerUtils.MESSAGE, "Topology Jar or Classpath cannot be empty");
			return Response.serverError().entity(jsonObject.toString()).build();
		}
		TopologyMetadata metadata = (TopologyMetadata) createTopologyMetadata.get("Topology");

		return submitService.submitTopology(metadata);
	}
}

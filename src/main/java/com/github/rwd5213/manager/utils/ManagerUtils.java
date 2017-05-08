package com.github.rwd5213.manager.utils;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rwd5213.manager.persistence.TopologyMetadata;

public class ManagerUtils {
	private static final Logger logger = LoggerFactory.getLogger(ManagerUtils.class);
	
	public static final String FALSE = "false";
	public static final String MESSAGE = "Message";
	public static final String RESPONSE = "Response";
	public static final String SUCCESS = "Success";
	
	
	//TopologyMetadata JSON Fields
	public static final String DATASOURCES = "datasources";
	public static final String NAME = "name";
	public static final String CLASSPATH = "classpath";
	public static final String JAR = "jar";
	public static final String ARGUMENTS = "arguments";
	public static final String AUTHOR = "author";

	
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	public static Map<String, Object> validatePayload(JSONObject payloadObject, Map<String, Object> attributeMap){
		for(TopologyAttributes attributes : TopologyAttributes.values()){
			if(!payloadObject.isNull(attributes.getTopologyAttributeName())){
				attributeMap.put(attributes.getTopologyAttributeName(), payloadObject.get(attributes.getTopologyAttributeName()));
			}
		}
		return attributeMap;
	}
	
	
	public static Map<String, Object> getFinalCleanedMetadata(JSONObject payloadObject) {
		Map<String, Object>  responseMap = new HashMap<String, Object>();
		TopologyMetadata topologyData = null;
		Map<String, Object> attributeMap = new HashMap<String, Object>();
		//ValidateAttributes
		logger.info("Starting Validating Attributes");
		attributeMap = validatePayload(payloadObject, attributeMap);
		logger.info("Ending Validating Attributes for payload");
		
		if(attributeMap.isEmpty()){
			logger.error("getFinalCleanedMetadata Pearse error {}");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(SUCCESS, FALSE);
			jsonObject.put(MESSAGE, "JSON payload cannot be mapped to java pojo");
			responseMap.put(RESPONSE, Response.serverError().entity(jsonObject.toString()).build());
			return responseMap;
		}
		
		logger.info("Getting pojo from json");
		try {
			topologyData = getPojoFromJson(attributeMap);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("getFinalCleanedMetadata Pearse error {}");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(SUCCESS, FALSE);
			jsonObject.put(MESSAGE, "JSON payload cannot be mapped to java pojo");
			responseMap.put(RESPONSE, Response.serverError().entity(jsonObject.toString()).build());
			return responseMap;
		}
		
		
		responseMap.put("Topology", topologyData);
		
		return responseMap;
	}
	
	public static TopologyMetadata getPojoFromJson(Map<String, Object> attributeMap) throws IllegalArgumentException {
		return mapper.convertValue(attributeMap, TopologyMetadata.class);
	}
	
}

package com.github.rwd5213.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.junit.Test;

import com.github.rwd5213.manager.utils.ManagerUtils;

public class MangerUtilsTest {
	
	@Test
	public void testGetFinalCleanedMetadata() {
		JSONObject object = TopologyObjectCreate.getJSONObject();
		Map<String, Object> cleanedData = ManagerUtils.getFinalCleanedMetadata(object);
		assertTrue(cleanedData.containsKey("Topology"));
		
		//Tests wrong payload information
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put(ManagerUtils.JAR, 1);
		jsonObject1.put(ManagerUtils.ARGUMENTS, "Supposed to be a list<string>");
		Map<String, Object> badcleanedData1 = ManagerUtils.getFinalCleanedMetadata(jsonObject1);
		assertTrue(badcleanedData1.containsKey("Response"));
		assertEquals(1, badcleanedData1.size());
		Response response1 = (Response) badcleanedData1.get("Response");
		assertEquals(500, response1.getStatus());
		assertTrue(response1.getEntity().toString().contains("JSON payload cannot be mapped to java pojo"));
		
		//Tests Null attribute map
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("bad key", "badstuff");
		Map<String, Object> badcleanedData = ManagerUtils.getFinalCleanedMetadata(jsonObject);
		assertTrue(badcleanedData.containsKey("Response"));
		assertEquals(1, badcleanedData.size());
		Response response = (Response) badcleanedData.get("Response");
		assertEquals(500, response.getStatus());
		assertTrue(response.getEntity().toString().contains("JSON payload cannot be mapped to java pojo"));
	}
	
	
}

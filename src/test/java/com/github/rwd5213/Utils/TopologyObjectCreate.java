package com.github.rwd5213.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rwd5213.manager.persistence.TopologyMetadata;
import com.github.rwd5213.manager.utils.ManagerUtils;

public class TopologyObjectCreate {

	private static ObjectMapper mapper = new ObjectMapper();
	
	public static TopologyMetadata getData() throws IOException {
		JSONObject object = getPayload1();
		TopologyMetadata topologyMetadata = mapper.readValue(object.toString(), TopologyMetadata.class);
		return topologyMetadata;
	}
	
	public static JSONObject getJSONObject(){
		JSONObject object = getPayload1();
		return object;
	}
	
	public static JSONObject getPayload1(){
		JSONObject source = new JSONObject();
		List<String> arguments = Arrays.asList("redis.marathon.mesos", "1366", "topology");
		List<String> datasources = Arrays.asList("fcms", "datasources", "analytics");
		source.put(ManagerUtils.ARGUMENTS, arguments);
		source.put(ManagerUtils.AUTHOR, "Bob");
		source.put(ManagerUtils.CLASSPATH, "com.github.rwd5213.topology");
		source.put(ManagerUtils.DATASOURCES, datasources);
		source.put(ManagerUtils.JAR, "rwd5213-topology.jar");
		source.put(ManagerUtils.NAME, "topology");
		return source;
	}
	
}

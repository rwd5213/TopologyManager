package com.github.rwd5213.manager.utils;

public enum TopologyAttributes {
	
	NAME(ManagerUtils.NAME), JAR(ManagerUtils.JAR), ARGUMENTS(ManagerUtils.ARGUMENTS), DATASOURCES(ManagerUtils.DATASOURCES), 
	CLASSPATH(ManagerUtils.CLASSPATH), AUTHOR(ManagerUtils.AUTHOR);
	
	private String TopologyAttributeName;
	
	private TopologyAttributes(String topologyAttributeName){
		this.TopologyAttributeName = topologyAttributeName;
	}
	
	public String getTopologyAttributeName(){
		return TopologyAttributeName;
	}

}

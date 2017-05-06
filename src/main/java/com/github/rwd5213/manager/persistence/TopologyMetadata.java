package com.github.rwd5213.manager.persistence;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rwd5213.manager.utils.ManagerUtils;

import java.io.Serializable;
import java.util.List;

public class TopologyMetadata implements Serializable{
	
	 private static final long serialVersionUID = -5218361147092116620L;


	    @JsonProperty(ManagerUtils.NAME)
	    private String name;

	    @JsonProperty(ManagerUtils.JAR)
	    private String jar;

	    @JsonProperty(ManagerUtils.ARGUMENTS)
	    private List<String> arguments;

	    @JsonProperty(ManagerUtils.DATASOURCES)
	    private List<String> datasources;

	    @JsonProperty(ManagerUtils.CLASSPATH)
	    private String classpath;

	    @JsonProperty(ManagerUtils.AUTHOR)
	    private String author;

	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getJar() {
	        return jar;
	    }
	    public void setJar(String jar) {
	        this.jar = jar;
	    }

	    public void setDatasources(List<String> datasources) {
	        this.datasources = datasources;
	    }
	    public List<String> getDatasources() {
	        return datasources;
	    }

	    public List<String> getArguments() {
	        return arguments;
	    }
	    public void setArguments(List<String> arguments) {
	        this.arguments = arguments;
	    }

	    public String getClasspath() {
	        return classpath;
	    }

	    public void setClasspath(String classpath) {
	        this.classpath = classpath;
	    }

	    public  void setAuthor (String author){
	        this.author = author;
	    }

	    public String getAuthor() {
	        return author;
	    }


}

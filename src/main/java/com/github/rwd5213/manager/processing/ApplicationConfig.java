package com.github.rwd5213.manager.processing;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import com.github.rwd5213.manager.resource.SubmitResource;

@ApplicationPath("/manager")
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig(){
		register(SubmitResource.class);
	}
}

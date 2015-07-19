package org.springframework.tryout;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeServiceContext;

public class MyTryoutApp {
	
	public static void main(String[] args) {
		
		ComputeServiceContext context = ContextBuilder.newBuilder("aws-ec2")
				.buildView(ComputeServiceContext.class);
		
	}

}

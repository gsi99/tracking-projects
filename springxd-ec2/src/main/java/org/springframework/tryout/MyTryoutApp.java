package org.springframework.tryout;

import static org.jclouds.ec2.options.RunInstancesOptions.Builder.asType;

import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xd.ec2.Ec2InstallerTest;
import org.jclouds.ContextBuilder;
import org.jclouds.aws.ec2.AWSEC2Api;
import org.jclouds.aws.ec2.features.AWSInstanceApi;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.RunNodesException;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.ec2.options.RunInstancesOptions;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.sshj.config.SshjSshClientModule;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

public class MyTryoutApp {
	
	static final Logger LOGGER = LoggerFactory.getLogger(MyTryoutApp.class);

	public static void main(String[] args) {

		String accesskeyid = "";
		String secretkey = "";
		String group = "micro-test";
		
		String region = "us-west-2";
		String zone = "us-west-2b";
		String ami = "ami-c7d092f7";
		int numberNodes = 1;
		String securityGroup = "sg-17620d72";

		//listNodes(accesskeyid, secretkey);

//		try {
//			//runInstance(accesskeyid, secretkey);
//		} catch (RunNodesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Iterable<Module> modules = new ImmutableSet.Builder<Module>()
		           .add(new SshjSshClientModule())
		           .add(new SLF4JLoggingModule())
		           .build();
		
		AWSEC2Api client = ContextBuilder.newBuilder("aws-ec2")
				.credentials(accesskeyid, secretkey)
				.modules(modules)
				.buildApi(AWSEC2Api.class);
		
		AWSInstanceApi instanceApi = client.getInstanceApi().get();
		RunInstancesOptions	instancesOptions = asType("t2.micro");
		instancesOptions = instancesOptions.withKeyName(accesskeyid);
		instancesOptions = instancesOptions.withSecurityGroup(securityGroup);
		
		//instancesOptions = instancesOptions.withUserData(script.getBytes());
		

		instanceApi.runInstancesInRegion(region, zone, ami, numberNodes, numberNodes, instancesOptions);
	}

	private static void runInstance(String accesskeyid, String secretkey) throws RunNodesException {
		ComputeServiceContext context = ContextBuilder.newBuilder("aws-ec2")
				.credentials(accesskeyid, secretkey)
				.buildView(ComputeServiceContext.class);
		ComputeService computeService = context.getComputeService();
		
		computeService.createNodesInGroup("xdserver", 1);
	}

	private static void listNodes(String accesskeyid, String secretkey) {
		ComputeServiceContext context = ContextBuilder.newBuilder("aws-ec2")
				.credentials(accesskeyid, secretkey)
				.buildView(ComputeServiceContext.class);
		ComputeService computeService = context.getComputeService();
		Set<? extends ComputeMetadata> nodeList = computeService.listNodes();

		for (Iterator iterator = nodeList.iterator(); iterator.hasNext();) {
			ComputeMetadata computeMetadata = (ComputeMetadata) iterator.next();
			LOGGER.info("List of nodes is below");
			LOGGER.info(computeMetadata.getName());
			System.out.println("List of nodes is below");
			System.out.println(computeMetadata.getName());
		}
	}
}

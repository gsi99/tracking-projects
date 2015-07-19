package org.springframework.xd.ec2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.xd.cloud.Deployer;
import org.springframework.xd.cloud.Deployment;
import org.springframework.xd.ec2.cloud.AWSDeployer;


public class Ec2InstallerTest {

	static final Logger LOGGER = LoggerFactory.getLogger(Ec2InstallerTest.class);
	AbstractApplicationContext ctx;
	BufferedReader br;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(
				"xdinstaller-context.xml");		
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGetEc2InstallerBean() {
//		assertNotNull("ctx was not found.", ctx);
//		
//		ctx.registerShutdownHook();
//		ctx.refresh();
//		// Begin Installation
//		Ec2Installer installer = ctx.getBean(Ec2Installer.class);
//		assertNotNull("Bean Ec2Installer was not found.", installer);
//		
//		try {
//			//installer.install();
//		}
//		catch (DeployTimeoutException te) {
//			te.printStackTrace();
//			fail("Failed with exception calling the installer");
//		}		
//	}
	
//	@Test
//	public void testGetPropertiesAndValidate() {
//		// Begin Installation
//		Ec2Installer installer = ctx.getBean(Ec2Installer.class);
//		Properties properties = installer.getProperties();
//		assertTrue("Properties were not retrieved", properties.size() > 0);
//		assertEquals("Cluster name not the expected value:" + properties.getProperty("cluster-name"), properties.getProperty("cluster-name"), "Parcels XD Cluster");
//		try {
//			installer.validateConfiguration(properties);
//		} catch (Exception e) {
//			fail("Propeerties failed validation");
//		}
//	}
	
	@Test public void testDeployment() {
		LOGGER.debug("Start of testDeployment");
		Ec2Installer installer = ctx.getBean(Ec2Installer.class);
		Deployer deployer;
		final Properties properties = installer.getProperties();
		//String awsAccessKey = getConsoleValue("Enter your aws-access-key: ");
		//String awsSecretKey = getConsoleValue("Enter your aws-secret-key: ");
		String awsAccessKey = "AKIAJP2LC5IPI7TDUB6Q";
		String awsSecretKey = "+YwxAExU8GajIa6chpU6C+6kn5iYCqELYDpMXNQ9";

		properties.setProperty("aws-access-key", awsAccessKey);
		properties.setProperty("aws-secret-key", awsSecretKey);

		installer.validateConfiguration(properties);
		installer.removeArtifacts();

		deployer = new AWSDeployer(properties);
		LOGGER.debug("Calling deployer");
		final List<Deployment> result = deployer.deploy();
		LOGGER.debug("Completed deployer call");
	}
	
	public String getConsoleValue(String prompt) {
        System.out.print(prompt);
        String value = null;
        try {
			value = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}

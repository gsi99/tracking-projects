package org.springframework.xd.ec2;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.xd.cloud.DeployTimeoutException;
import org.springframework.xd.cloud.Deployer;
import org.springframework.xd.cloud.Deployment;
import org.springframework.xd.ec2.cloud.AWSDeployer;

public class Ec2InstallerTest {

	AbstractApplicationContext ctx;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(
				"xdinstaller-context.xml");		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEc2InstallerBean() {
		assertNotNull("ctx was not found.", ctx);
		
		ctx.registerShutdownHook();
		ctx.refresh();
		// Begin Installation
		Ec2Installer installer = ctx.getBean(Ec2Installer.class);
		assertNotNull("Bean Ec2Installer was not found.", installer);
		
		try {
			//installer.install();
		}
		catch (DeployTimeoutException te) {
			te.printStackTrace();
			fail("Failed with exception calling the installer");
		}		
	}
	
	@Test
	public void testGetPropertiesAndValidate() {
		// Begin Installation
		Ec2Installer installer = ctx.getBean(Ec2Installer.class);
		Properties properties = installer.getProperties();
		
		assertTrue("Properties were not retrieved", properties.size() > 0);
		assertEquals("Cluster name not the expected value:" + properties.getProperty("cluster-name"), properties.getProperty("cluster-name"), "Parcels XD Cluster");
		try {
			installer.validateConfiguration(properties);
		} catch (Exception e) {
			fail("Propeerties failed validation");
		}
	}
	
	@Test public void testDeployment() {
		Ec2Installer installer = ctx.getBean(Ec2Installer.class);
		Deployer deployer;
		final Properties properties = installer.getProperties();
		installer.validateConfiguration(properties);
		installer.removeArtifacts();
		deployer = new AWSDeployer(properties);
		final List<Deployment> result = deployer.deploy();
	}

}

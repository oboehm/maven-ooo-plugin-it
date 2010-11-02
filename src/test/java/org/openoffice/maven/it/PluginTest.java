/*************************************************************************
 * PluginTest.java
 *
 * The Contents of this file are made available subject to the terms of
 * either of the GNU Lesser General Public License Version 2.1
 * 
 * GNU Lesser General Public License Version 2.1
 * =============================================
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 *
 * Contributor(s): oliver.boehm@agentes.de
 ************************************************************************/

package org.openoffice.maven.it;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.junit.*;

import fit.Counts;


/**
 * This is the integration test class for the maven-ooo-plugin.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (01.11.2010)
 */
public class PluginTest {
	
	private static final String ARTIFACT_ID = "ooo-ext-test";
	private static final Log log = LogFactory.getLog(PluginTest.class);
	private static MavenRunner mvnRunner = new MavenRunner();
	
	/**
	 * Clean archetype directory - the created archetype directory will be
	 * removed.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@AfterClass
	public static void cleanArchetypeDirectory() throws IOException {
		File workingDir = mvnRunner.getCommandline().getWorkingDirectory();
		File archetypeDir = new File(workingDir, ARTIFACT_ID);
		log.info("cleaning " + archetypeDir + "...");
		FileUtils.deleteDirectory(archetypeDir);
	}
	
	/**
	 * This test calls 'mvn archetype:create ...' as described in
	 * {@link "http://wiki.services.openoffice.org/wiki/OpenOffice_Maven2_Integration#Generating_an_Extension_project"}.
	 * 
	 * @throws CommandLineException if mvn command fails
	 */
	//@Test
	public void testArchetypeCreate() throws CommandLineException {
		String[] args = { "archetype:create",
				"-DgroupId=org.openoffice.dev.tests",
				"-DartifactId=" + ARTIFACT_ID,
				"-Dversion=1.0",
				"-DpackageName=org.openoffice.dev",
				"-DarchetypeGroupId=org.openoffice.dev",
				"-DarchetypeArtifactId=maven-ooo-plugin",
				"-DarchetypeVersion=1.1.1-SNAPSHOT" };
		int returnValue = mvnRunner.run(args);
		assertEquals(0, returnValue);
	}
	
	/**
	 * This test should start all the different mvn commands which are listed
	 * on {@link "http://github.com/oboehm/maven-ooo-plugin-it/wiki"}.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void startIntegrationTest() throws IOException {
		FitRunner fitRunner = new FitRunner(new File("target", "result.html"));
		Counts counts = fitRunner.run();
        assertEquals(0, counts.exceptions);
        assertEquals(0, counts.wrong);
	}

}

/*************************************************************************
 * SmokeTest.java
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.junit.Ignore;
import org.junit.Test;
import org.openoffice.maven.it.fixture.MvnShell;


/**
 * A simple SmokeTest to test some basic stuff.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (01.11.2010)
 */
public class SmokeTest {
	
	private static final Log log = LogFactory.getLog(SmokeTest.class);
	
	/**
	 * This is only a test to if Maven could be started via
	 * command line.
	 */
	@Test
	public void testMaven() throws CommandLineException {
		MvnShell mvnShell = new MvnShell();
		mvnShell.addArgument("-v");
		mvnShell.run();
		int returnValue = mvnShell.getExitCode();
        log.info("returnValue = " + returnValue);
        assertEquals(0, returnValue);
	}
	
	/**
	 * To run the CmdShell together with the UNO interface successful we
	 * should have enough memory. Experiments with MAVEN_OPTS shows that
	 * MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=256m" was too less for some
	 * test, MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=512m" was enough.
	 * So we test if there is enough memory available.
	 * <br/>
	 * If this test fails add at least "-Xmx768m" to the VM arguments.
	 * <br/>
	 * Because this is the only test which fails on my MacBook it will
	 * be ignored now (ob, 07-Feb-11).
	 */
	@Test
	@Ignore
	public void testMemory() {
		long maxMemory = Runtime.getRuntime().maxMemory() / 0x100000;
		log.info("max. memory: " + maxMemory + " MB");
		assertTrue(maxMemory + " MB are not enough (recommended: 512 MB)",
				maxMemory > 240);
	}

}

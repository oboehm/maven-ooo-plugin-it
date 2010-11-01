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
import org.codehaus.plexus.util.cli.*;
import org.junit.Test;


/**
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
		MavenRunner mvnRunner = new MavenRunner();
		String[] args = { "-v" };
        int returnValue = mvnRunner.run(args);
        log.info("returnValue = " + returnValue);
        assertEquals(0, returnValue);
	}

}

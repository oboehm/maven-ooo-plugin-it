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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import fit.Counts;


/**
 * This is the integration test class for the maven-ooo-plugin.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (01.11.2010)
 */
public class PluginTest {
	
	private static final Log log = LogFactory.getLog(PluginTest.class);
	
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
		log.info("counts=" + counts);
        assertEquals(0, counts.exceptions);
        assertEquals(0, counts.wrong);
	}

}

/*************************************************************************
 * MavenVerifierTest.java
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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.Test;

/**
 * This is a functional test for the maven-ooo-plugin for Maven.
 * See {@link "http://maven.apache.org/plugin-developers/plugin-testing.html"}
 * for more information how to write integration/functional tests for
 * Maven plugins.
 * 
 * @author oliver
 * @since 1.1 (12.01.2011)
 */
public class MavenVerifierTest {
	
	private static final Log log = LogFactory.getLog(MavenVerifierTest.class);
    
    /**
     * Test validate.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws VerificationException the verification exception
     */
    @Test
    public void testValidate() throws IOException, VerificationException {
        File testDir = ResourceExtractor.simpleExtractResources(this.getClass(), "project/demo");
        assertTrue("does not exist: " + testDir, testDir.exists());
        log.info("testDir=" + testDir);
        Verifier verifier = new Verifier(testDir.getAbsolutePath());
        verifier.executeGoal("validate");
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
    }

}


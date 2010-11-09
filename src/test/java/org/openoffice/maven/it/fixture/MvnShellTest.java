/*************************************************************************
 * MvnShellTest.java
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

package org.openoffice.maven.it.fixture;

import static org.junit.Assert.*;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.junit.Test;

/**
 * JUnit test for MvnShell.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (05.11.2010)
 */
public final class MvnShellTest {
	
	private MvnShell mvnShell = new MvnShell();

	/**
	 * Test method for {@link MvnShell#addArgument(java.lang.String)}.
	 */
	@Test
	public void testAddArgument() {
		assertFalse(mvnShell.hasArguments());
		mvnShell.addArgument("-v");
		assertTrue(mvnShell.hasArguments());
	}

	/**
	 * Test method for {@link org.openoffice.maven.it.fixture.MvnShell#getExitCode()}.
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetExitCode() {
		mvnShell.getExitCode();
	}

	/**
	 * Test method for {@link org.openoffice.maven.it.fixture.MvnShell#run()}.
	 *
	 * @throws CommandLineException the command line exception
	 */
	@Test
	public void testRun() throws CommandLineException {
		mvnShell.addArgument("-v");
		mvnShell.run();
		assertEquals(0, mvnShell.getExitCode());
	}

	/**
	 * Test method for {@link org.openoffice.maven.it.fixture.MvnShell#run()}.
	 *
	 * @throws CommandLineException the command line exception
	 */
	@Test
	public void testRunNotOK() throws CommandLineException {
		mvnShell.addArgument("dummy");
		mvnShell.run();
		assertEquals(1, mvnShell.getExitCode());
	}
	
	/**
	 * Only for temporary tests.
	 *
	 * @throws CommandLineException the command line exception
	 */
//	@Test
	public void testRunValidate() throws CommandLineException {
		mvnShell.changeWorkingDir("ooo-ext-test");
		mvnShell.addArgument("validate");
		mvnShell.run();
		assertEquals(0, mvnShell.getExitCode());
	}
	


}

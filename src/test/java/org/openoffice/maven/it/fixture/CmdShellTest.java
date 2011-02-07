/*************************************************************************
 * CmdShellTest.java
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
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (14.11.2010)
 *
 */
public class CmdShellTest {

	/**
	 * Test method for {@link CmdShell#run()}. As test command we use
	 * 'unopkg' from OpenOffice.
	 *
	 * @throws CommandLineException the command line exception
	 */
	@Test
	public void testRunUnopkg() throws CommandLineException {
		CmdShell unopkg = new CmdShell("unopkg");
		unopkg.addArgument("-h");
		unopkg.run();
		assertEquals(0, unopkg.getExitCode());
	}
	
	/**
	 * The changeWorkDir() method should also be able to handle system
	 * properties like <tt>${user.home}</tt>.
	 * 
	 * @throws CommandLineException
	 */
	@Test
	public void testChangeWorkingDir() throws CommandLineException {
		CmdShell cd = new CmdShell("cd");
		cd.changeWorkingDir("${user.home}");
		cd.addArgument(".m2");
		cd.run();
		assertEquals(0, cd.getExitCode());
	}

}

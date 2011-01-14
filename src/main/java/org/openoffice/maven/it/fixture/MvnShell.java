/*************************************************************************
 * MvnShell.java
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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This is a simple wrapper around the Maven command to be called from an 
 * ActionFixture.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (02.11.2010)
 * {@link "http://fit.c2.com/wiki.cgi?NetworkExample"}
 */
public final class MvnShell extends CmdShell {
	
	static final Log log = LogFactory.getLog(MvnShell.class);
	/** We want to start 'mvn' in batch mode. */
	static final String[] defaultArgs = { "-B" };

	/**
	 * Instantiates a new mvn shell.
	 */
	public MvnShell() {
		super("mvn");
		commandline.addArguments(defaultArgs);
	}

	/**
	 * Checks for arguments.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean hasArguments() {
		return commandline.getArguments().length > defaultArgs.length;
	}

}

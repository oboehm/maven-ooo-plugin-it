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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.*;

import fit.Fixture;

/**
 * This is a simple wrapper around the Maven command to be called from an 
 * ActionFixture.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (02.11.2010)
 * {@link "http://fit.c2.com/wiki.cgi?NetworkExample"}
 */
public final class MvnShell extends Fixture {
	
	private static final Log log = LogFactory.getLog(MvnShell.class);
	private final Commandline commandline = new Commandline("mvn");
	private Integer exitCode;
	
	/**
	 * Instantiates a new mvn shell.
	 */
	public MvnShell() {
		initWorkingDirectory();
	}
	
	private void initWorkingDirectory() {
		String tmpDir = FileFixture.getWorkingDir().getPath();
		commandline.setWorkingDirectory(tmpDir);
	}

	/**
	 * Adds the argument.
	 *
	 * @param arg e.g. "--help"
	 */
	public void addArgument(final String arg) {
		this.commandline.addArguments(new String[] { arg });
	}
	
	/**
	 * Checks for arguments.
	 *
	 * @return true, if successful
	 */
	public boolean hasArguments() {
		return this.commandline.getArguments().length > 0;
	}
	
	/**
	 * Runs (or executes) the Maven command.
	 *
	 * @throws CommandLineException the command line exception
	 */
	public void run() throws CommandLineException {
        CommandLineUtils.StringStreamConsumer output = new CommandLineUtils.StringStreamConsumer();
        CommandLineUtils.StringStreamConsumer error = new CommandLineUtils.StringStreamConsumer();
        log.info("starting " + commandline + "...");
        exitCode = CommandLineUtils.executeCommandLine(commandline, output, error);
        String outmsg = output.getOutput().trim();
        if (StringUtils.isNotEmpty(outmsg)) {
            log.info(outmsg);
        }
        String errmsg = error.getOutput().trim();
        if (StringUtils.isNotEmpty(errmsg)) {
            log.warn(errmsg);
        }
	}
	
	/**
	 * Gets the exit code of the last started Maven command.
	 *
	 * @return the exit code
	 */
	public int getExitCode() {
		if (this.exitCode == null) {
			throw new IllegalStateException(this.commandline + " not yet executed");
		}
		return this.exitCode.intValue();
	}

}

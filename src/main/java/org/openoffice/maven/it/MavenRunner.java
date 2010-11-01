/*************************************************************************
 * MavenRunner.java
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.*;

/**
 * This is a simple wrapper around the Maven command.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (01.11.2010)
 */
public class MavenRunner {
	
	private static final Log log = LogFactory.getLog(MavenRunner.class);
	private final Commandline commandline = new Commandline("mvn");
	
	/**
	 * Instantiates a new maven runner.
	 */
	public MavenRunner() {
		initWorkingDirectory();
	}
	
	private void initWorkingDirectory() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		commandline.setWorkingDirectory(tmpDir);
	}
	
	/**
	 * Gets the commandline.
	 *
	 * @return the commandline
	 */
	public Commandline getCommandline() {
		return this.commandline;
	}
	
	/**
	 * Runs the Maven command with the given arguments.
	 *
	 * @param args the args
	 * @return the return value of the mvn command
	 * @throws CommandLineException the command line exception
	 */
	public int run(final String[] args) throws CommandLineException {
		commandline.addArguments(args);
        CommandLineUtils.StringStreamConsumer output = new CommandLineUtils.StringStreamConsumer();
        CommandLineUtils.StringStreamConsumer error = new CommandLineUtils.StringStreamConsumer();
        log.info("starting " + commandline + "...");
        int returnValue = CommandLineUtils.executeCommandLine(commandline, output, error);
        String outmsg = output.getOutput().trim();
        if (StringUtils.isNotEmpty(outmsg)) {
            log.info(outmsg);
        }
        String errmsg = error.getOutput().trim();
        if (StringUtils.isNotEmpty(errmsg)) {
            log.warn(errmsg);
        }
        return returnValue;
	}

}

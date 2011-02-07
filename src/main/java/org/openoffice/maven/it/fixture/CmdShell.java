package org.openoffice.maven.it.fixture;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.helpers.OptionConverter;
import org.codehaus.plexus.util.cli.*;
import org.openoffice.maven.Environment;

import fit.Fixture;

/**
 * This is a little command shell based on Plexus' CommandLine.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (14.11.2010)
 */
public class CmdShell extends Fixture {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(CmdShell.class);
	
	/** The commandline. */
	protected Commandline commandline;
	
	/** The exit code. */
	private Integer exitCode;
	
	/**
	 * Instantiates a new command shell.
	 */
	public CmdShell() {
		super();
	}

	/**
	 * Instantiates a new command shell.
	 *
	 * @param command the command
	 */
	public CmdShell(final String command) {
		super();
		setCommand(command);
	}
	
	/**
	 * Sets the command.
	 *
	 * @param command the new command
	 */
	public void setCommand(final String command) {
		commandline = new Commandline(command);
		initWorkingDirectory();
		initPath();
	}

	/**
	 * Inits the working directory.
	 */
	private void initWorkingDirectory() {
		String tmpDir = FileFixture.getWorkingDir().getPath();
		commandline.setWorkingDirectory(tmpDir);
	}
	
	/**
	 * Inits the path.
	 */
	private void initPath() {
		try {
			Environment.setUpFor(commandline);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Change working dir.
	 *
	 * @param dirname the dirname
	 */
	public void changeWorkingDir(final String dirname) {
		if (this.commandline == null) {
			throw new IllegalStateException("no command set - call setCommand(..) before!");
		}
		String normalized = OptionConverter.substVars(dirname, System.getProperties());
		File dir = new File(normalized);
		if (!dir.isAbsolute()) {
			File workingDir = commandline.getWorkingDirectory();
			dir = new File(workingDir, normalized);
		}
		commandline.setWorkingDirectory(dir);
	}

	/**
	 * Adds the argument.
	 *
	 * @param arg e.g. "--help"
	 */
	public void addArgument(final String arg) {
		commandline.addArguments(new String[] { arg });
	}

	/**
	 * Checks for arguments.
	 *
	 * @return true, if successful
	 */
	public boolean hasArguments() {
		return commandline.getArguments().length > 0;
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
			throw new IllegalStateException(commandline + " not yet executed");
		}
		return this.exitCode.intValue();
	}

}
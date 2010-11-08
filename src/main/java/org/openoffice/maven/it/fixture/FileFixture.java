/*************************************************************************
 * FileCmd.java
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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fit.ColumnFixture;

// TODO: Auto-generated Javadoc
/**
 * This helper is needed to delete or check files and directories.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (05.11.2010)
 */
public class FileFixture extends ColumnFixture {
	
	/** The log. */
	private static Log log = LogFactory.getLog(FileFixture.class);
	
	/** The working dir. */
	private static File workingDir = getTmpDir();

	/** Used by FIT framework. */
	public String file;

	/** Used by FIT framework. */
	public String comment;
	
	/**
	 * Gets the working dir.
	 *
	 * @return the working dir
	 */
	public static File getWorkingDir() {
		return workingDir;
	}
	
	/**
	 * Gets the tmp dir.
	 *
	 * @return the tmp dir
	 */
	public static File getTmpDir() {
		String tmpDirName = System.getProperty("java.io.tmpdir");
		return new File(tmpDirName);
	}
	
	/**
	 * Delete the given directory. The path for the directory is relative to
	 * the presetted working directory.
	 *
	 * @param dirname the dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void deleteDir(final String dirname) throws IOException {
		File dir = new File(workingDir, dirname);
		log.info("cleaning " + dir + "...");
		FileUtils.deleteDirectory(dir);
	}
	
	/**
	 * Checks if file exists.
	 * 
	 * @return true if file exists
	 */
	public boolean exists() {
		return new File(workingDir, file).exists();
	}
	
	/**
	 * Checks if file is a file.
	 *
	 * @return true, if is file
	 */
	public boolean isFile() {
		return new File(workingDir, file).isFile();
	}
	
	/**
	 * Checks if file is a directory.
	 *
	 * @return true, if is dir
	 */
	public boolean isDir() {
		return new File(workingDir, file).isDirectory();
	}
	
	/**
	 * Returns the type of the file.
	 *
	 * @return "file", "dir" or error message
	 */
	public String type() {
		File f = new File(workingDir, file);
		if (!f.exists()) {
			return "not-existing";
		}
		if (f.isFile()) {
			return "file";
		}
		if (f.isDirectory()) {
			return "dir";
		}
		return "unknown";
	}

}

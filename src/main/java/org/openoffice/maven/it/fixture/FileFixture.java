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

import fit.Fixture;

/**
 * This helper is needed to delete or check files and directories.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (05.11.2010)
 */
public class FileFixture extends Fixture {
	
	private static Log log = LogFactory.getLog(FileFixture.class);
	private static File workingDir = getTmpDir();
	
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

}

/*************************************************************************
 * FileFixtureTest.java
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

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * JUnit test for FileFixture.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (12.12.2010)
 */
public class FileFixtureTest {
	
	private FileFixture fixture = new FileFixture();
	
	/**
	 * Test method for {@link org.openoffice.maven.it.fixture.FileFixture#type()}.
	 */
	@Test
	public void testType() {
		File tmpDir = FileFixture.getTmpDir();
		fixture.file = tmpDir.getAbsolutePath();
		assertEquals("dir", fixture.type());
	}
	
	/**
	 * It should be also possible to use e.g. "${user.home}/.m2/repository" as
	 * filename (which is the path to the local Maven repository). 
	 */
	@Test
	public void testTypeWithVariableFilename() {
		fixture.file = "${user.home}/.m2/repository";
		assertEquals("dir", fixture.type());
	}
	
	/**
	 * Tests if files with variables inside (e.g.
	 * "${user.home}/.m2/repository/abc") are deleted.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testDeleteDir() throws IOException {
		String dirname = "dir" + System.currentTimeMillis();
		File testDir = new File(FileFixture.getTmpDir(), dirname);
		assertTrue("can't be created: " + testDir, testDir.mkdir());
		FileFixture.deleteDir("${java.io.tmpdir}/" + dirname);
		assertFalse("not deleted: " + testDir, testDir.exists());
	}

}

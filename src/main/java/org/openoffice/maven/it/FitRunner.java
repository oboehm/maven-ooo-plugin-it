/*************************************************************************
 * FitRunner.java
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

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fit.Counts;
import fit.FileRunner;

/**
 * This is the entry point to start the integration test with FIT.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 1.1.1 (02.11.2010)
 * @see "http://fit.c2.com"
 * @see fit.FileRunner
 */
public class FitRunner extends FileRunner {
	
	private static final Log log = LogFactory.getLog(FitRunner.class);
	private static final URI INPUT_URI;
	private final File outputFile;
	
	static {
		try {
			INPUT_URI = new URI("http://github.com/oboehm/maven-ooo-plugin-it");
		} catch (URISyntaxException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * Instantiates a new FitRunner.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FitRunner() throws IOException {
		this(File.createTempFile("maven-ooo-plugin-it-", ".html"));
	}
	
	/**
	 * Instantiates a new FitRunner.
	 *
	 * @param outputFile the output file
	 */
	public FitRunner(final File outputFile) {
		this.outputFile = outputFile;
	}
	
	/**
	 * Reads the input URI and processes the HTML.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see FileRunner#run(String[])
	 */
	@SuppressWarnings("unchecked")
	public Counts run() throws IOException {
		this.fixture.summary.put("input URI", INPUT_URI);
        this.fixture.summary.put("output file", this.outputFile);
        this.input = read(INPUT_URI);
        this.output = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFile)));
        this.process();
        log.info("result written to " + this.outputFile.getAbsolutePath());
        this.output.close();
        return this.fixture.counts;
	}

	private static String read(URI uri) throws IOException {
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.content-charset",
				"UTF-8");
		HttpMethod method = new GetMethod(uri.toString());
		int statusCode = client.executeMethod(method);
		String content = method.getResponseBodyAsString();
		method.releaseConnection();
		if (statusCode != HttpStatus.SC_OK){
			throw new IOException(uri + " returned with " + statusCode);
		}
		return content;
	}

}

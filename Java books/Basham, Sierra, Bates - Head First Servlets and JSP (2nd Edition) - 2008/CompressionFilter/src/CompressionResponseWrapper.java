package com.example;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * This response wrapper decorates the original response object by add a
 * compression decorator on the original servlet output stream.
 */
class CompressionResponseWrapper extends HttpServletResponseWrapper {

  /** the compressed output stream for the servlet response */
  private GZIPServletOutputStream servletGzipOS = null;

  /** the PrintWriter object to the compressed output stream */
  private PrintWriter pw = null;

  /**
   * The constructor performs the Decorator responsibility of storing
   * a reference to the object being decorated, in this case the HTTP
   * response object.
   */
  CompressionResponseWrapper(HttpServletResponse resp) {
    super(resp);
  }

  //
  // Override HttpServletResponse methods to use our stream objects
  //

  /**
   * This state variable holds the first stream object created.
   */
  private Object streamUsed = null;

  /**
   * Provide access to a decorated servlet output stream.
   */
  public ServletOutputStream getOutputStream() throws IOException {
      // Allow the servlet to access a servlet output stream (SOS)
      // only if the servlet has not already accessed the print writer.
    if ( (streamUsed != null) && (streamUsed != servletGzipOS) ) {
      throw new IllegalStateException();
    }
    // Wrap the original servlet output stream with our compression SOS.
    // We'll look at this class in a minute.
    if ( servletGzipOS == null ) {
      servletGzipOS
	  = new GZIPServletOutputStream(getResponse()
					.getOutputStream());
      streamUsed = servletGzipOS;
    }
    return servletGzipOS;
  }

  /**
   *  Provide access to a decorated print writer.
   */
  public PrintWriter getWriter() throws IOException {
    // Allow the servlet to access a print writer only if the
    // servlet has not already accessed the servlet output stream.
    if ( (streamUsed != null) && (streamUsed != pw) ) {
      throw new IllegalStateException();
    }
    // To make a print writer, we have to first wrap the servlet output stream
    // and then wrap the compression SOS in two additional OS decorators:
    // OutputStreamWriter which converts characters into bytes, and then a
    // PrintWriter on top of the OSWriter object.
    if ( pw == null ) {
      servletGzipOS
	  = new GZIPServletOutputStream(getResponse().getOutputStream());
      // Use the response charset to create the OSWriter
      OutputStreamWriter osw
	  = new OutputStreamWriter(servletGzipOS,
				   getResponse().getCharacterEncoding());
      // Wrap the OSWriter in the PrintWriter
      pw = new PrintWriter(osw);
      streamUsed = pw;
    }
    return pw;
  }

  /**
   * Ignore this method, because the real output will be compressed.
   */
  public void setContentLength(int len) { }

  //
  // Decorator methods (used by the Filter)
  //

  /**
   * This gives the compression filter a handle on the GZIP output stream
   * so that it (the filter) can "finish" and flush the GZIP stream.
   */
  public GZIPOutputStream getGZIPOutputStream() {
    return this.servletGzipOS.internalGzipOS;
  }
}


/**
 * This helper class is a Decorator on the ServletOutputStream abstract
 * class which delegates the real work of compressing the generated content
 * using a standard GZIP output stream.
 * 
 * There is only one abstract method in the SOS class which this Decorator
 * must implement: write(int).  This is where all of the delegation magic
 * occurs.
 */
class GZIPServletOutputStream extends ServletOutputStream {

  /**
   * Keep a reference to raw GZIP stream.
   * This instance variable is package-private to allow the compression
   * response wrapper access to this variable.
   */
  GZIPOutputStream internalGzipOS;

  /** Decorator constructor */
  GZIPServletOutputStream(ServletOutputStream sos) throws IOException {
    this.internalGzipOS = new GZIPOutputStream(sos);
  }

  /**
   * This method implements the compression decoration by delegating the
   * write() call to the GZIP compression stream, which is wrapping the
   * original servlet output stream, which is (ultimately) wrapping the
   * TCP network output stream to the client.
   */
  public void write(int param) throws java.io.IOException {
    internalGzipOS.write(param);
  }
}

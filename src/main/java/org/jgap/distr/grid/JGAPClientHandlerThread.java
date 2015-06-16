/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package org.jgap.distr.grid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import org.homedns.dade.jcgrid.message.GridMessage;
import org.homedns.dade.jcgrid.message.GridMessageVFSSessionFileRequest;
import org.homedns.dade.jcgrid.message.GridMessageVFSSessionFileResult;
import org.homedns.dade.jcgrid.server.ClientHandlerThread;
import org.homedns.dade.jcgrid.server.GridServer;

/**
 * Handles JGAP-specific messages on the client-side that are not originally
 * supported by JCGrid.
 *
 * @author Klaus Meffert
 * @since 3.2
 */
public class JGAPClientHandlerThread
    extends ClientHandlerThread {
  /** String containing the CVS revision. Read out via reflection!*/
  private final static String CVS_REVISION = "$Revision: 1.2 $";

  public JGAPClientHandlerThread(GridServer server, Socket socket)
      throws IOException {
    super(server, socket);
  }

  protected void handleMsg(GridMessage msg)
      throws Exception {
    if (msg instanceof GridMessageVFSSessionFileRequest) {
      String n = ( (GridMessageVFSSessionFileRequest) msg).getName();
      // Read the file.
      // --------------
      File f = new File(super.gridServer.getVFSSessionPool().getPath(),n);
      long fsize = f.length();
      if (log.isDebugEnabled())
        log.debug("  File size: " + fsize);
      /**@todo consider 4GB limit*/
      byte[] data = new byte[ (int) fsize];
      FileInputStream fis = new FileInputStream(f);
      fis.read(data);
      fis.close();
      handlerChannel.send(new GridMessageVFSSessionFileResult(data));
    }
    else {
      super.handleMsg(msg);
    }
  }
}

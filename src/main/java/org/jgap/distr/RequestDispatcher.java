package org.jgap.distr;

import java.io.IOException;

public abstract class RequestDispatcher {
  public abstract void dispatch(IWorker a_worker, WorkerCommand a_command)
      throws IOException;
}

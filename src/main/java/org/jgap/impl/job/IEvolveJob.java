/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package org.jgap.impl.job;

/**
 * Interface for a job to do evolution (via JGAP, but possibly via other
 * frameworks).
 *
 * @author Klaus Meffert
 * @since 3.2
 */
public interface IEvolveJob
    extends IJob {
  /** String containing the CVS revision. Read out via reflection!*/
  final static String CVS_REVISION = "$Revision: 1.2 $";
}

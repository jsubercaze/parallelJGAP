/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package org.jgap.distr.grid.gp;

import org.homedns.dade.jcgrid.message.GridMessageWorkRequest;
import org.homedns.dade.jcgrid.message.GridMessageWorkResult;
import org.jgap.Configuration;
import org.jgap.event.EventManager;
import org.jgap.gp.BaseGPChromosome;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.GPProgramBase;
import org.jgap.gp.impl.BranchTypingCross;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPPopulation;
import org.jgap.gp.impl.GPProgram;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.gp.terminal.NOP;
import org.jgap.impl.DefaultCloneHandler;
import org.jgap.impl.DefaultCompareToHandler;
import org.jgap.impl.DefaultInitializer;
import org.jgap.impl.JGAPFactory;
import org.jgap.impl.StockRandomGenerator;

import com.thoughtworks.xstream.XStream;

/**
 * Specialized version of XStream for JGAP.
 *
 * @author Klaus Meffert
 * @since 3.3.4
 */
public class JGAPGPXStream
    extends XStream {
  /** String containing the CVS revision. Read out via reflection!*/
  private final static String CVS_REVISION = "$Revision: 1.2 $";
  public JGAPGPXStream() {
    super();
    init(this);
  }

  protected void init(XStream a_xstream) {
    // Use aliases to reduce storage capacity.
    // ---------------------------------------
    a_xstream.alias("conf", Configuration.class);
    a_xstream.alias("gpconf", GPConfiguration.class);
    a_xstream.alias("gppop", GPPopulation.class);
    a_xstream.alias("gpprgbase", GPProgramBase.class);
    a_xstream.alias("gpprg", GPProgram.class);
    a_xstream.alias("basegpchrom", BaseGPChromosome.class);
    a_xstream.alias("prgchrom", ProgramChromosome.class);
    a_xstream.alias("cgene", CommandGene.class);
    a_xstream.alias("requestgp", JGAPRequestGP.class);
    a_xstream.alias("resultgp", JGAPResultGP.class);
    a_xstream.alias("factory", JGAPFactory.class);
    a_xstream.alias("stockrandomgen", StockRandomGenerator.class);
    a_xstream.alias("branchxover", BranchTypingCross.class);
    a_xstream.alias("defclonehandler", DefaultCloneHandler.class);
    a_xstream.alias("defcomphandler", DefaultCompareToHandler.class);
    a_xstream.alias("definit", DefaultInitializer.class);
    a_xstream.alias("gridmessworkreq", GridMessageWorkRequest.class);
    a_xstream.alias("gridmessworkres", GridMessageWorkResult.class);

    a_xstream.alias("eventman", EventManager.class);
    a_xstream.alias("defgpfiteval", DefaultGPFitnessEvaluator.class);
    a_xstream.alias("gpfitfunc", GPFitnessFunction.class);
    a_xstream.alias("tournsel", org.jgap.gp.impl.TournamentSelector.class);
    a_xstream.alias("void", java.lang.Void.class);
    a_xstream.alias("Jbool", java.lang.Boolean.class);
    a_xstream.alias("Jint", java.lang.Integer.class);
    a_xstream.alias("Jdouble", java.lang.Double.class);
    a_xstream.alias("Jfloat", java.lang.Float.class);
    a_xstream.alias("random", java.util.Random.class);
    a_xstream.alias("nop", NOP.class);
  }
}

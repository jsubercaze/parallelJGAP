/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package org.jgap.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jgap.BulkFitnessFunction;
import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.JGAPTestCase;
import org.jgap.Population;

/**
 * Tests for the BulkFitnessOffsetRemover class.
 *
 * @author Achim Westermann
 * @since 2.2
 */
public class BulkFitnessOffsetRemoverTest
extends JGAPTestCase {
	/** String containing the CVS revision. Read out via reflection! */
	private final static String CVS_REVISION = "$Revision: 1.12 $";

	// A plainforward implementation for this test.
	//---------------------------------------------
	private final transient FitnessFunction m_fitnessFunction =
			new DummyFitnessFunction();

	// The instance that is tested.
	//-----------------------------
	private final transient BulkFitnessFunction m_bulkFitnessFunction =
			new BulkFitnessOffsetRemover(m_fitnessFunction);

	public static Test suite() {
		final TestSuite suite = new TestSuite(BulkFitnessOffsetRemoverTest.class);
		return suite;
	}

	/**
	 * Test the inner class DummyFitnessFunction that is needed for this test with
	 * IntegerGenes in a Chromosome.
	 *
	 * @throws Exception
	 */
	public void testDummyFitnessFunction_0()
			throws Exception {
		// setting up a Chromosome and assign values:
		//-------------------------------------------
		final NumberGene[] genes = {
				new IntegerGene(conf),
				new IntegerGene(conf),
				new IntegerGene(conf)};
		final Number[] values = {
				new Integer(100),
				new Integer(200),
				new Integer(300)};
		for (int i = 0; i < genes.length; i++) {
			genes[i].setAllele(values[i]);
		}
		final Chromosome chromosome = new Chromosome(conf, genes);
		// Using the DummyFitnessFunction:
		//--------------------------------
		assertNotNull(m_fitnessFunction);
		final double fitness = m_fitnessFunction.getFitnessValue(chromosome);
		assertEquals( 100 + 200 + 300, fitness, 0.0);
	}

	/**
	 * Test the inner class DummyFitnessFunction that is needed for this test with
	 * a mix of IntegerGenes and DoubleGenes in a Chromosome.
	 *
	 * @throws Exception
	 */
	public void testDummyFitnessFunction_1()
			throws Exception {
		// setting up a Chromosome and assign values:
		//-------------------------------------------
		final NumberGene[] genes = {
				new IntegerGene(conf),
				new DoubleGene(conf),
				new DoubleGene(conf)};
		final Number[] values = {
				new Integer(100),
				new Double(2000.25),
				new Double(0.75e11)};
		for (int i = 0; i < genes.length; i++) {
			genes[i].setAllele(values[i]);
		}
		final Chromosome chromosome = new Chromosome(conf, genes);
		// Using the DummyFitnessFunction:
		//--------------------------------
		assertNotNull(m_fitnessFunction);
		final double fitness = m_fitnessFunction.getFitnessValue(chromosome);
		assertEquals( 100 + 2000.25 + 0.75e11, fitness, 0.0);
	}

	public void testConstructor_0() {
		assertNotNull(m_fitnessFunction);
		new BulkFitnessOffsetRemover(m_fitnessFunction);
	}

	public void testConstructor_1() {
		try {
			final BulkFitnessOffsetRemover test = new BulkFitnessOffsetRemover(null);
			fail("The constructor of "
					+ test.getClass().getName() + " allows a null value!");
		}
		catch (final Throwable f) {
			; //this is OK
		}
	}

	/**
	 * Tests the method BulkFitnessOffsetRemover.evaluate(java.util.List) with a
	 * list of two Chromosomes with each one IntegerGene with the allele 100.
	 *
	 * @author Achim Westermann
	 * @since 2.2
	 * @throws Exception
	 */
	public void testEvaluate_0()
			throws Exception {
		assertNotNull(m_fitnessFunction);
		assertNotNull(m_bulkFitnessFunction);
		// setting up two Chromosomes and assign values:
		//--------------------------------------------------
		final Gene g100A = new IntegerGene(conf);
		g100A.setAllele(new Integer(100));
		final Gene g100B = new IntegerGene(conf);
		g100B.setAllele(new Integer(100));
		final Chromosome c100A = new Chromosome(conf, new Gene[] {g100A});
		final Chromosome c100B = new Chromosome(conf, new Gene[] {g100B});
		// Running the evaluate method.
		// It will remove the common offset 100 and add 1.
		//-------------------------------------------------
		final Population chromosomeList = new Population(conf);
		chromosomeList.addChromosome(c100A);
		chromosomeList.addChromosome(c100B);
		m_bulkFitnessFunction.evaluate(chromosomeList);
		// The offset should have been removed:
		//-------------------------------------
		assertEquals(1.0, c100A.getFitnessValue(), 0d);
		assertEquals(1.0, c100B.getFitnessValue(), 0d);
	}

	/**
	 * Tests the method BulkFitnessOffsetRemover.evaluate(List) with a list of two
	 * Chromosomes with one IntegerGene with the allele 100. The IntegerGene is
	 * the same instance in both Chromosomes.
	 *
	 * @author Achim Westermann
	 * @since 2.2
	 * @throws Exception
	 */
	public void testEvaluate_1()
			throws Exception {
		assertNotNull(m_fitnessFunction);
		assertNotNull(m_bulkFitnessFunction);
		// setting up two Chromosomes and assign values:
		//--------------------------------------------------
		final Gene G100 = new IntegerGene(conf);
		G100.setAllele(new Integer(100));
		final Chromosome C100A = new Chromosome(conf, new Gene[] {G100});
		final Chromosome C100B = new Chromosome(conf, new Gene[] {G100});
		// Running the evaluate method.
		// It will remove the common offset 100 and add 1.
		//-------------------------------------------------
		final Population chromosomeList = new Population(conf);
		chromosomeList.addChromosome(C100A);
		chromosomeList.addChromosome(C100B);
		m_bulkFitnessFunction.evaluate(chromosomeList);
		// The offset should have been removed:
		//-------------------------------------
		assertEquals(1.0, C100A.getFitnessValue(), 0d);
		assertEquals(1.0, C100B.getFitnessValue(), 0d);
	}

	/**
	 * Tests the method BulkFitnessOffsetRemover.evaluate(List) with a list of two
	 * Chromosomes with each one IntegerGene with the allele 200. The Chromosomes
	 * ar the same instance in the List that is evaluated.
	 *
	 * @author Achim Westermann
	 * @since 2.2
	 * @throws Exception
	 */
	public void testEvaluate_2()
			throws Exception {
		assertNotNull(m_fitnessFunction);
		assertNotNull(m_bulkFitnessFunction);
		// setting up two Chromosomes and assign values:
		//--------------------------------------------------
		final Gene G200 = new IntegerGene(conf);
		G200.setAllele(new Integer(200));
		final Chromosome C200 = new Chromosome(conf, new Gene[] {G200});
		// Running the evaluate method.
		// It will remove the common offset 100 and add 1.
		//-------------------------------------------------
		final Population chromosomeList = new Population(conf);
		chromosomeList.addChromosome(C200);
		chromosomeList.addChromosome(C200);
		m_bulkFitnessFunction.evaluate(chromosomeList);
		// The offset should have been removed:
		//-------------------------------------
		assertEquals(1.0, C200.getFitnessValue(), 0d);
	}

	/**
	 * Tests the method BulkFitnessOffsetRemover.evaluate(List) with a list of two
	 * Chromosomes with each two NumberGenes. <pre>
	 *                  chromosomeA
	 *                 /           \
	 *             IntegerGene   DoubleGene   => Fitness 4100.15
	 *                |             |
	 *              100            4000.15
	 *                  chromosomeB
	 *                 /           \
	 *             IntegerGene   DoubleGene   =>; Fitness 1234 + 14e-5
	 *                |             |
	 *              1234           14e-5
	 *  </pre>
	 *
	 * @author Achim Westermann
	 * @since 2.2
	 * @throws Exception
	 */
	public void testEvaluate_3()
			throws Exception {
		assertNotNull(m_fitnessFunction);
		assertNotNull(m_bulkFitnessFunction);
		// setting up a two Chromosome instances and assign values:
		//---------------------------------------------------------
		final NumberGene[] genesA = {
				new IntegerGene(conf), new DoubleGene(conf)};
		final Number[] valuesA = {
				new Integer(100), new Double(4000.15)};
		for (int i = 0; i < genesA.length; i++) {
			genesA[i].setAllele(valuesA[i]);
		}
		final Chromosome chromosomeA = new Chromosome(conf, new Gene[] {
				genesA[0],
				genesA[1]});
		final NumberGene[] genesB = {
				new IntegerGene(conf), new DoubleGene(conf)};
		final Number[] valuesB = {
				new Integer(1234), new Double(14.e-5)};
		for (int i = 0; i < genesA.length; i++) {
			genesB[i].setAllele(valuesB[i]);
		}
		final Chromosome chromosomeB = new Chromosome(conf, new Gene[] {
				genesB[0],
				genesB[1]});
		// Calculation of the estimated result:
			//--------------------------------------
			final double offset = Math.min( ( (IntegerGene) genesA[0]).intValue()
					+ ( (DoubleGene) genesA[1]).doubleValue(),
					( (IntegerGene) genesB[0]).intValue()
					+ ( (DoubleGene) genesB[1]).doubleValue());
			final double estimateFitnessA = ( (IntegerGene) genesA[0]).intValue()
					+ ( (DoubleGene) genesA[1]).doubleValue() - offset + 1d;
			final double estimateFitnessB = ( (IntegerGene) genesB[0]).intValue()
					+ ( (DoubleGene) genesB[1]).doubleValue() - offset + 1d;
			// Running the evaluate method.
			// It will remove the common offset.
			//-------------------------------------------------
			final Population chromosomeList = new Population(conf);
			chromosomeList.addChromosome(chromosomeA);
			chromosomeList.addChromosome(chromosomeB);
			m_bulkFitnessFunction.evaluate(chromosomeList);
			// The offset should have been removed:
			//-------------------------------------
			assertEquals(estimateFitnessA, chromosomeA.getFitnessValue(), 0d);
			assertEquals(estimateFitnessB, chromosomeB.getFitnessValue(), 0d);
	}

	/**
	 *
	 * @author Klaus Meffert
	 * @since 2.2
	 * @throws Exception
	 */
	public void testGetAbsoluteFitness_0()
			throws Exception {
		final BulkFitnessOffsetRemover remover = new BulkFitnessOffsetRemover(
				new StaticFitnessFunction(33.345d));
		final NumberGene[] genes = {
				new IntegerGene(conf),
				new IntegerGene(conf),
				new IntegerGene(conf)};
		final Number[] values = {
				new Integer(100),
				new Integer(200),
				new Integer(300)};
		for (int i = 0; i < genes.length; i++) {
			genes[i].setAllele(values[i]);
		}
		final Chromosome chrom = new Chromosome(conf, genes);
		double fitness = remover.getAbsoluteFitness(chrom);
		assertEquals(33.345d, chrom.getFitnessValue(), DELTA);
		assertEquals(33.345d, fitness, DELTA);
		fitness = remover.getAbsoluteFitness(chrom);
		assertEquals(33.345d, chrom.getFitnessValue(), DELTA);
		assertEquals(33.345d, fitness, DELTA);
	}

	/**
	 * <p>
	 * This class is a helper to allow testing class
	 * {@link BulkFitnessOffsetRemover}in a plainforward way: It only works
	 * with {@link NumberGene}instances and returns the addition of their
	 * primitive values as the fitness value. This way we can configure a
	 * Chromosome with numerical value genes knowing their fitness and see what
	 * fitness values should be installed by the method
	 * {@link BulkFitnessOffsetRemover}.
	 * </p>
	 * <p>
	 * Do not copy or use this implementation it is for this test only. It is
	 * tested itself by the outer class ({@link #testDummyFitnessFunction()})
	 * to ensure valid test results.
	 * </p>
	 *
	 * @author Achim Westermann
	 * @since 2.2
	 */
	private class DummyFitnessFunction
	extends FitnessFunction {
		/**
		 * Casts every Gene to the subtype of NumberGene and calculates the sum
		 * of the numerical values obtained.
		 *
		 * @param a_subject the Chromosome to be evaluated
		 * @return the sum of all numerical values of the Genes within the
		 * given Chromosome that only consists of NumberGene instances
		 * @throws ClassCastException if a Gene within the Chromosome is not derived
		 * from {@link NumberGene}
		 *
		 * @see org.jgap.FitnessFunction#evaluate(org.jgap.Chromosome)
		 */
		@Override
		public double evaluate(final IChromosome a_subject) {
			double ret = 0d;
			final Gene[] genes = a_subject.getGenes();
			for (int i = genes.length - 1; i > -1; i--) {
				if (genes[i] instanceof DoubleGene) {
					ret += ( (DoubleGene) genes[i]).doubleValue();
				}
				else if (genes[i] instanceof IntegerGene) {
					ret += ( (IntegerGene) genes[i]).intValue();
				}
				else {
					throw new ClassCastException(
							"The FitnessFunction "
									+ getClass().getName()
									+ " is for testing purposes only and only handles DoubleGene "
									+ "and NumberGene instances (used: "
									+ genes[i].getClass().getName() + ")");
				}
			}
			return ret;
		}
	}
}

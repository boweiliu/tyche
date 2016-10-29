package io.neysofu.tyche
package stats

import scala.util.Random

/** A collection of useful probaiblity distribution generators.
 */
object Commons {

  /** Returns a discrete uniform probability distribution such that every
   *  one of the possible values has equal probability.
   */
  def newDiscreteUniform[A](seq: Seq[A]): DiscreteGen[A] = new DiscreteGen[A] {
    val pmf = seq.map(x => (1.0/seq.size, x))
  }

  /** Returns a degenerate (deterministic) probability distribution.
   */
  def newDegenerate[A](v: A): DiscreteGen[A] = newDiscreteUniform(
    Seq(v)
  )

  /** Returns a Bernoulli probability distribution with Boolean outcomes.
   */
  def newBernoulli(p: Double): DiscreteGen[Boolean] = new DiscreteGen[Boolean] {
    val pmf = Seq((1-p, false), (p, true))
  }

  /** Returns a continuous uniform probability distribution in the interval
   *  ´[0;1[´.
   */
  def newUniform(): SamplingGen[Double] = new SamplingGen[Double] {
    def get = rnd.nextDouble
  }
 
  /** Returns a Gaussian probability distribution.
   */
  def newGauss(sd: Double, eg: Double): SamplingGen[Double] = {
    new SamplingGen[Double] {
      def get = rnd.nextGaussian * sd + eg
    }
  }

  /** Returns a normal probability distribution.
   */
  def newStdNormal(): SamplingGen[Double] = newGauss(1, 0)

  /** Returns a chi-squared distribution with ´k´ degrees of freedom.
   */
  def newChiSquare(k: Int): SamplingGen[Double] = new SamplingGen[Double] {
    def get = Seq.fill(k)(Math.pow(newStdNormal.get, 2)).sum
  }

  /** Returns a binomial disribution with parameters ´n´ and ´p´.
   */
  def newBinomial(n: Int, p: Double): SamplingGen[Int] = new SamplingGen[Int] {
    def get = Seq.fill(n)(rnd.nextDouble).count(_ < p)
  }
}

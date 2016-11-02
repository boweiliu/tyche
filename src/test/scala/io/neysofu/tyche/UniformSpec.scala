package io.neysofu.tyche

import org.scalatest.{WordSpec, Matchers}

class UniformSpec extends WordSpec with Matchers {

  val gen = ContinuousDistribution.uniform
  val gen2 = new ContinuousDistribution[Double] {
    def get = Math.pow(random.nextDouble, 2)
  }

  "A uniform probability distribution" when {
    
    "sampled" should {
      "generate values in the ´[0,1[´ interval" in {
        gen.times(gen.sampleSize).forall(x => x >= 0 && x < 1)
      }
    }
  }
}
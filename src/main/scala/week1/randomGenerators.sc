import java.util.Random

import week1.Generator

val integers = new Generator[Int] {
  val rand = new Random()
  def generate = rand.nextInt()
}

val booleans = new Generator[Boolean] {
  def generate = integers.generate > 0
}

val pairs = new Generator[(Int, Int)] {
  def generate = (integers.generate, integers.generate)
}


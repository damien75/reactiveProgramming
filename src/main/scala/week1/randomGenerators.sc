import java.util.Random

import week1._

val integers = new Generator[Int] {
  val rand = new Random()
  def generate = rand.nextInt()
}

val booleans = for (x <- integers) yield x > 0

def pairs[T, U](t: Generator[T], u: Generator[U]) =
  t flatMap {x => u map {y => (x, y)}}

def single[T](x: T): Generator[T] = new Generator[T] {
  override def generate: T = x
}

def choose(lo: Int, hi: Int): Generator[Int] =
  for (x <- integers) yield lo + x % (hi - lo)

def oneOf[T](xs: T*): Generator[T] =
  for (idx <- choose(0, xs.length)) yield xs(idx)

oneOf("red", "blue", "yellow").generate

def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if (isEmpty) emptyLists else nonEmptyLists
} yield list

def emptyLists = single(Nil)

def nonEmptyLists = for {
  head <- integers
  tail <- lists
} yield head :: tail

def test[T](r: Generator[T], noTimes: Int = 100)(test: T => Boolean): Unit = {
  for (_ <- 0 until noTimes) {
    val value = r.generate
    assert(test(value), "Test failed for: " + value)
  }
  println("Test passed " + noTimes + " times")
}

test(pairs(lists, lists)){ case (xs, ys) => (xs ++ ys).length > xs.length}

def leafs: Generator[Leaf] = for {
  x <- integers
} yield Leaf(x)

def inners: Generator[Inner] = for {
  l <- trees
  r <- trees
} yield Inner(l, r)

def trees: Generator[Tree] = for {
  isLeaf <- booleans
  tree <- if (isLeaf) leafs else inners
} yield tree

trees.generate


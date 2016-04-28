package week1

/**
  * Created by damiengoblot on 2016-04-27.
  */
trait Tree {

}

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

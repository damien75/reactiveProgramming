package week1

/**
  * Created by damiengoblot on 2016-04-27.
  */
trait Generator[+T] {
  def generate: T
}

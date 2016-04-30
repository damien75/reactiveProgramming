package week2

/**
  * Created by damiengoblot on 2016-04-30.
  */
trait Subscriber {
  def handler(pub: Publisher)
}

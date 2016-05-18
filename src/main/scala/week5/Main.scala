package week5

import akka.actor.{Actor, Props, ReceiveTimeout}
import scala.concurrent.duration._

/**
  * Created by damiengoblot on 2016-05-17.
  */
class Main extends Actor {

  import Receptionist._

  val receptionist = context.actorOf(Props[Receptionist], "receptionist")

  receptionist ! Get("http://www.google.com")

  context.setReceiveTimeout(10.seconds)

  def receive = {
    case Result(url, set) =>
      println(set.toVector.sorted.mkString(s"Results for '$url':\n", "\n", "\n"))
    case Failed(url) =>
      println("Failed to fetch {}\n", url)
    case ReceiveTimeout =>
      context.stop(self)
  }

  override def postStop(): Unit = {
    WebClient.shutdown()
  }
}

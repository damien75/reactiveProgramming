package week5

import akka.actor._
import scala.concurrent.duration._

/**
  * Created by damiengoblot on 2016-05-17.
  */
object Controller {
  case class Check(url: String, depth: Int)
  case class Result(links: Set[String])
}

class Controller extends Actor with ActorLogging {
  import Controller._
  context.setReceiveTimeout(10.seconds)
  var cache = Set.empty[String]
  var children = Set.empty[ActorRef]
  def receive = {
    case Check(url, depth) =>
      log.debug("{} checking {}", depth, url)
      if (!cache(url) && depth > 0)
        children += context.actorOf(Props(new Getter(url, depth - 1)))
      cache += url
    case Getter.Done =>
      children -= sender
      if (children.isEmpty) context.parent ! Result(cache)
    case ReceiveTimeout =>
      children foreach (_ ! Getter.Abort)
  }
}

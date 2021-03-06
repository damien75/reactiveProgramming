package week5

import akka.actor.{Status, Actor}
import org.jsoup.Jsoup
import akka.pattern.pipe
import scala.collection.JavaConverters._


/**
  * Created by damiengoblot on 2016-05-17.
  */
object Getter {
  case object Done
  case object Abort
}

class Getter(url: String, depth: Int) extends Actor {

  import Getter._

  implicit val executor = context.dispatcher

  WebClient get url pipeTo self

  def receive = {
    case body: String =>
      for (link <- findLinks(body))
        context.parent ! Controller.Check(link, depth)
      stop()
    case _: Status.Failure => stop()
    case Abort => stop()
  }

  def stop(): Unit = {
    context.parent ! Done
    context.stop(self)
  }

  def findLinks(body: String): Iterator[String] = {
    val document = Jsoup.parse(body, url)
    val links = document.select("a[href]")
    for {
      link <- links.iterator().asScala
    } yield link.absUrl("href")
  }
}
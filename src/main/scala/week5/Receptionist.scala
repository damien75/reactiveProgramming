package week5

import akka.actor.{Actor, Props, ActorRef}

/**
  * Created by damiengoblot on 2016-05-17.
  */
object Receptionist {
  private case class Job(client: ActorRef, url: String)
  case class Get(url: String)
  case class Result(url: String, links: Set[String])
  case class Failed(url: String)
}

class Receptionist extends Actor{
  import Receptionist._

  var reqNo = 0

  def receive = waiting

  def runNext(queue: Vector[Job]): Receive = {
    if (queue.isEmpty) waiting
    else {
      val controller = context.actorOf(Props[Controller], s"c$reqNo")
      controller ! Controller.Check(queue.head.url, 2)
      running(queue)
    }
  }
  def enqueueJob(queue: Vector[Job], job: Job): Receive = {
    if (queue.size > 3) {
      sender ! Failed(job.url)
      running(queue)
    } else running(queue :+ job)
  }
  val waiting: Receive = {
    case Get(url) => context.become(runNext(Vector(Job(sender, url))))
  }
  def running(queue: Vector[Job]): Receive = {
    case Controller.Result(links) =>
      val job = queue.head
      job.client ! Result(job.url, links)
      context.stop(sender)
      context.become(runNext(queue.tail))
    case Get(url) =>
      context.become(enqueueJob(queue, Job(sender, url)))
  }
}

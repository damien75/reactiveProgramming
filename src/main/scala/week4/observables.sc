import rx.lang.scala.Observable
import scala.concurrent.duration._

val ticks: Observable[Long] = Observable.interval(1 seconds)

val evens: Observable[Long] = ticks.filter(_%2 == 0)

val bufs: Observable[Seq[Long]] = evens.slidingBuffer(2, 1)

val s = bufs.subscribe(println(_))

readLine()

s.unsubscribe()
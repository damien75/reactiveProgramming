package week2

/**
  * Created by damiengoblot on 2016-04-30.
  */
class Consolidator(observed: List[BankAccount]) extends Subscriber{
  observed.foreach(_.subscribe(this))

  private var total: Int = _
  compute()

  private def compute() =
    total = observed.map(_.currentBalance).sum

  def handler(pub: Publisher) = compute()

  def totalBalance: Int = total
}

package week2

/**
  * Created by damiengoblot on 2016-04-30.
  */
class Consolidator(observed: List[BankAccount]){
  def consolidated(): Signal[Int] =
    Signal(observed.map(_.balance()).sum)
}

package week2

/**
  * Created by damiengoblot on 2016-04-29.
  */
class BankAccount {
  val balance = Var(0)

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      val oldBalance = balance()
      balance() = oldBalance + amount
    }
  }

  def withdraw(amount: Int): Unit = {
    if (amount > 0 && amount <= balance()) {
      val oldBalance = balance()
      balance() = oldBalance - amount
    }
    else throw new Error("insufficient funds on your accounts to withdraw " + amount)
  }
}

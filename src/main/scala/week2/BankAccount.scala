package week2

/**
  * Created by damiengoblot on 2016-04-29.
  */
class BankAccount extends Publisher {
  private var balance = 0
  def currentBalance: Int = balance

  def deposit(amount: Int): Unit = {
    if (amount > 0) balance += amount
    publish()
  }

  def withdraw(amount: Int): Unit = {
    if (amount > 0 && amount <= balance) {
      balance -= amount
      publish()
    }
    else throw new Error("insufficient funds on your accounts to withdraw " + amount)
  }
}

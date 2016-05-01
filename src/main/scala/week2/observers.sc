import week2.{Consolidator, BankAccount}

val a = new BankAccount
val b = new BankAccount
val c = new Consolidator(List(a, b))
val d = c.consolidated()

a deposit 20
d()
b deposit 30
d()

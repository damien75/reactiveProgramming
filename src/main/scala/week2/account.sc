import week2.{Var, Signal, Consolidator, BankAccount}

val a = new BankAccount()
val b = new BankAccount()
val c = new Consolidator(List(a, b))
val d = c.consolidated()

d()
a deposit 20
d()
b deposit 30
d()
val xchange = Var(246.00)
val inDollar = Signal(d()*xchange())
inDollar()
b withdraw 10
inDollar()
xchange() = 572.00
inDollar()
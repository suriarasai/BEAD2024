object SimpleFunctions {
  println("Welcome to the Scala worksheet")
  def abs(x: Double) = if (x >= 0) x else -x

  println(abs(3))
  println(abs(-3))

  def fac(n: Int) = {
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  }

  println(fac(10))

  def recursiveFac(n: Int): Int =
    if (n <= 0) 1 else n * recursiveFac(n - 1)

  println(recursiveFac(10))

  def decorate(str: String, left: String = "[", right: String = "]") =
    left + str + right

  println(decorate("Hello"))

  println(decorate("Hello", "<<<", ">>>"))

  println(decorate("Hello", ">>>["))

  println(decorate(left = "<<<", str = "Hello", right = ">>>"))

  println(decorate("Hello", right = "]<<<"))
  
  def sum(args: Int*) = {
  var result = 0
  for (arg <- args) result += arg
  result
}

val s = sum(1, 4, 9, 16, 25)
println(s)

val s2 = sum(1 to 5: _*)
println(s2)

def recursiveSum(args: Int*) : Int = {
  if (args.length == 0) 0
  else args.head + recursiveSum(args.tail : _*)
}

println(recursiveSum(1, 4, 9, 16, 25))

import java.text.MessageFormat

val str = MessageFormat.format("The answer to {0} is {1}", "everything",
                        42.asInstanceOf[AnyRef])
println(str)
  

}
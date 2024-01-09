object ControlStructure {
  println("Welcome to the Scala worksheet")
  // Conditional Expresssions
  val xx = 0

  if (xx > 0) 1 else -1

  val s = if (xx > 0) 1 else -1

  if (xx > 0) "positive" else -1

  if (xx > 0) 1

  //Statement Termination
  val n = 10
  val r = 1

  //if (n > 0) { r = r * n; n -= 1 }

  //val s, s0, v, v0, t = 0.0

  //s = s0 + (v - v0) * t + // The + tells the parser that this is not the end
  //0.5 * (a - a0) * t * t

  //if (n > 0) {
  //r = r * n
  //n -= 1
  //}

  val x0 = 1.0
  val y0 = 1.0
  var x = 4.0
  var y = 5.0

  import scala.math._

  val distance = { val dx = x - x0; val dy = y - y0; sqrt(dx * dx + dy * dy) }

  //var r = 1
  //var n = 10 { r = r * n; n -= 1 } // Has value Unit

  //x = y = 1 // No--can't assign Unit to x

  print("Answer: ")
  println(42)

  println("Answer: " + 42)

  printf("Hello, %s! You are %d years old.%n", "Fred", 42)

  val name = "Fred"
  val age = 42.333333

  //print(f"Hello, $name! You are $age%7.2f years old%n")

  import scala.io
 // val name2 = StdIn.readLine("Your name: ")

  print("Your age: ")
 // val age2 = StdIn.readInt()

 // println(f"Hello, $name2! Next year, you will be ${age2 + 1}.", name, age + 1)
 

}
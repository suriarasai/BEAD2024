object Loop {
  println("Welcome to the Scala worksheet")

  var r = 1
  var n = 10

  while (n > 0) {
    r = r * n
    n -= 1
  }

  println(r)

  r = 1
  n = 10

  for (i <- 1 to n)
    r = r * i

  println(r)

  val s = "Hello"
  var sum = 0
  for (i <- 0 to s.length - 1)
    sum += s(i)

  println(sum)

  sum = 0
  for (ch <- "Hello") sum += ch

  import scala.util.control.Breaks._
  breakable {
    for (ch <- "Hello World") {
      if (ch == ' ') break; // Exits the breakable block
      println(ch)
    }
  }

  for (i <- 1 to 3; j <- 1 to 3) print((10 * i + j) + " ")

  for (i <- 1 to 3; j <- 1 to 3 if i != j) print((10 * i + j) + " ")

  for (i <- 1 to 3; from = 4 - i; j <- from to 3) print((10 * i + j) + " ")
  for {
    i <- 1 to 3
    from = 4 - i
    j <- from to 3
  } print((10 * i + j) + " ")

  for (i <- 1 to 10) yield i % 3

  for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar

  for (i <- 0 to 1; c <- "Hello") yield (c + i).toChar

}
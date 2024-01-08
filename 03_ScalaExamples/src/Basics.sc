object Basics {

  println("Welcome to the Scala worksheet")

  val answer = 8 * 5 + 2

  0.5 * answer

  val greeting: String = null

  //answer = 0 // This will give an error

  var counter = 0
  counter = 1 // Ok, can change a var

  var i, j = 0
  var greeting2, message: String = null

  //1.toString()

  1.to(10)

  "Hello".intersect("World")

  var counter1 = 0

  counter1 += 1

  val x: BigInt = 1234567890

  x * x * x

  import scala.math._

  sqrt(2)
  
  1.to(10)
  
  1.to(10).map(sqrt(_))
  
  pow(2, 4)
  min(3, Pi)

  "Hello".distinct

  BigInt.probablePrime(100, scala.util.Random)

  "Hello"(4)

  "Hello".apply(4)

  BigInt("1234567890")

  BigInt.apply("1234567890")

  BigInt("1234567890") * BigInt("112358111321")

  "Hello, World!".count(_.isUpper)
  
  "DinnerTable".containsSlice('i'.to('e'))
  
  "Scala".sorted

}
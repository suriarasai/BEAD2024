

package sg.edu.iss.bead.examples.morecollections

object UsingFatArrow {
  def fliesPerSecond(callback: () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }
  
  def main(args: Array[String]): Unit= {
    fliesPerSecond(() => println("Time and tide wait for none ..."))
  }
}


package sg.edu.iss.bead.examples.oop

object VariablesDemo {
   def main(args: Array[String]) {
      var myVar : Int = 50;
      val myVal : String = "Hello World! I've started learning Scala."; 

      myVar = 90;  
      //myVal = "Hello world!"        
      
      println(myVar); 
      println(myVal); 

   }
}
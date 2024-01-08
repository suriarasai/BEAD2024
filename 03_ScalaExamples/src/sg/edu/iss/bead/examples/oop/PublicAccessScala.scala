package sg.edu.iss.bead.examples.oop

class OuterClass {
   class InnerClass {
      def printName() { println("Hello world, my name is Suria!") }
      
      class InnerMost {
         printName() // OK
      }
   }
   (new InnerClass).printName() // OK because now printName() is public
}
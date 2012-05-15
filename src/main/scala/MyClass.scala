/**
  * Example program to sort out trait/case class construction.
  * MyTrait's x, y and z properties are erased by the overrides in MyCaseClass and the
  * anonymous class that it extends.
  * Scala does not allow vars to be used for any of the properties of this example.
  * @see http://lampwww.epfl.ch/~magarcia/ScalaCompilerCornerReloaded/2011Q2/ConstrPhase.pdf
  * @author Mike Slinn
  */

/**  */
trait MyTrait {
  /** Cannot override a mutable variable so this cannot be a var */
  val x: String = "X set by MyTrait initializaton"
  val y: String = "Y set by MyTrait initializaton"
  val z: String = "Z set by MyTrait initializaton"

  def showX = println("MyTrait.x=" + x)
}

/** @param x erases MyTrait.x and completely replaces it, even from the point of view of methods
 *           defined in the trait
 *  @param y erases MyTrait.y and completely replaces it, even from the point of view of methods
 *           defined in the trait */
case class MyCaseClass(override val x: String, override val y: String = "Y default") extends {
  override val z: String = "Z set by the initialization of the anonymous class extended by MyCaseClass"
} with MyTrait

object Main extends App {
  val myCaseClass = MyCaseClass("X set by case class construction")
  println("myCaseClass = " + myCaseClass)
  println("myCaseClass.x=" + myCaseClass.x)
  println("myCaseClass.y=" + myCaseClass.y)
  println("myCaseClass.z=" + myCaseClass.z)
  myCaseClass.showX

  val myCaseClass2 = MyCaseClass("X set by case class construction", "Y set by case class construction")
  println("myCaseClass2 = " + myCaseClass2)
}
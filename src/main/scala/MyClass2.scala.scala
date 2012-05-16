/**
 * Example program to sort out trait/case class construction.
 * MyTrait's x, y and z properties are erased by the overrides in MyCaseClass and the
 * anonymous class that it extends.
 * Scala does not allow vars to be used for any of the properties of this example.
 *
 * Output is:
 * {{{
 * myCaseClass = MyCaseClass(X set by case class construction,Y default)
 * myCaseClass.x=X set by case class construction
 * myCaseClass.y=Y default
 * myCaseClass.z=Z set by the initialization of the anonymous class extended by MyCaseClass
 * MyTrait.x=X set by case class construction
 * myCaseClass2 = MyCaseClass(X set by case class construction,Y set by case class construction)
 * }}}
 * @see http://lampwww.epfl.ch/~magarcia/ScalaCompilerCornerReloaded/2011Q2/ConstrPhase.pdf
 * @author Mike Slinn
 */

class BaseKlass(val x: String, val y: String, val z: String) {
  def showX = println("%s.showX: x = %s".format(getClass.getName, x))
}

/**@param x erases MyTrait.x and completely replaces it, even from the point of view of methods
 *          defined in the trait
 * @param y erases MyTrait.y and completely replaces it, even from the point of view of methods
 *          defined in the trait */
case class MyKlass(override val x: String, override val y: String, override val z: String) extends BaseKlass(x, y, z)

object Main2 extends App {
  val baseKlass = new BaseKlass("X set by BaseKlass construction", "Y set by BaseKlass construction", "Z set by BaseKlass construction")
  baseKlass.showX
  println("baseKlass.x = " + baseKlass.x)
  println("baseKlass.y = " + baseKlass.y)
  println("baseKlass.z = " + baseKlass.z)

  val myKlass = MyKlass("X set by MyKlass construction", "Y set by MyKlass construction", "Z set by MyKlass construction")
  println("myKlass = " + myKlass)
  myKlass.showX
}
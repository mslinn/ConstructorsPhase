/**
 * Example program to sort out case/case class construction.
 * Output is:
 * {{{
 * BaseKlass.showX: x = X set by BaseKlass construction
 * baseKlass.x = X set by BaseKlass construction
 * baseKlass.y = Y set by BaseKlass construction
 * baseKlass.z = Z set by BaseKlass construction
 * myKlass = MyKlass(X set by MyKlass construction,Y set by MyKlass construction,Z set by MyKlass construction)
 * MyKlass.showX: x = X set by MyKlass construction
 * }}}
 * @see http://lampwww.epfl.ch/~magarcia/ScalaCompilerCornerReloaded/2011Q2/ConstrPhase.pdf
 * @author Mike Slinn
 */

class BaseKlass(val x: String, val y: String, val z: String) {
  def showX = println("%s.showX: x = %s".format(getClass.getName, x))
}

/**@param x erases BaseKlass.x and completely replaces it, even from the point of view of methods
 *          defined in the trait
 * @param y erases BaseKlass.y and completely replaces it, even from the point of view of methods
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
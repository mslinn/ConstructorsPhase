/**
 * Example program to sort out case/case class construction.
 * Output is:
 * {{{
 * BaseKlass.showX: x = X set by BaseKlass2 construction
 * baseKlass.x = X set by BaseKlass2 construction
 * baseKlass.y = Y set by BaseKlass2 construction
 * baseKlass.z = Z set by BaseKlass2 construction
 * myKlass = MyKlass(X set by MyKlass2 construction,Y set by MyKlass2 construction,Z set by MyKlass2 construction)
 * MyKlass.showX: x = X set by MyKlass2 construction
 * }}}
 * @see http://lampwww.epfl.ch/~magarcia/ScalaCompilerCornerReloaded/2011Q2/ConstrPhase.pdf
 * @author Mike Slinn
 */

class BaseKlass2(val x: String, val y: String, val z: String) {
  def showX = println("%s.showX: x = %s".format(getClass.getName, x))
}

class Enclosure()(implicit val msg: String) {
  case class MyKlass2(override val x: String, override val y: String, override val z: String) extends BaseKlass2(x, y, z)
}

object Main3 extends App {
  val baseKlass = new BaseKlass2("X set by BaseKlass2 construction", "Y set by BaseKlass2 construction", "Z set by BaseKlass2 construction")
  val enclosure = new Enclosure()("Set by implicit")
  val myKlass = enclosure.MyKlass2("X set by MyKlass2 construction", "Y set by MyKlass2 construction", "Z set by MyKlass2 construction")
  println("myKlass = " + myKlass)
}
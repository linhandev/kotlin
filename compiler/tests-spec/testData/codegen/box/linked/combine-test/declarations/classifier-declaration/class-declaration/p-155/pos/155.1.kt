// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 155 -> sentence 155
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 155 -> sentence 155
 *                inheritance, overriding -> paragraph 155 -> sentence 155
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 155 -> sentence 155
 * NUMBER: 1
 * DESCRIPTION: subclass override of open superclass member combined with constructor delegation is dynamically dispatched through supertype-typed references in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val base: Int) {
    open fun f(): Int = base
}

class Child(base: Int, val extra: Int) : Base(base) {
    override fun f(): Int = base + extra
}

// TESTCASE NUMBER: 2
open class Shape(val name: String) {
    open val area: Int get() = 0
}

class Square(val side: Int) : Shape("square") {
    override val area: Int get() = side * side
}

// TESTCASE NUMBER: 3
open class Greeter(val who: String) {
    open fun greet(): String = "hi $who"
}

class Loud(who: String) : Greeter(who) {
    override fun greet(): String = "HI $who!"
}

fun viaBaseRef(): Pair<Int, Int> {
    val plain: Base = Base(10)
    val overridden: Base = Child(10, 5)
    return plain.f() to overridden.f()
}

fun viaShapeList(): List<Int> {
    val shapes: List<Shape> = listOf(Shape("raw"), Square(3), Square(4))
    return shapes.map { it.area }
}

fun viaGreeter(): String {
    val g: Greeter = Loud("ann")
    return g.greet()
}

fun box(): String {
    if (viaBaseRef() != (10 to 15)) return "NOK: base-ref"
    if ((Child(2, 3) as Base).f() != 5) return "NOK: child-cast"
    if (viaShapeList() != listOf(0, 9, 16)) return "NOK: shapes"
    if (Square(5).area != 25) return "NOK: square-direct"
    if (viaGreeter() != "HI ann!") return "NOK: greeter"
    if (Greeter("bo").greet() != "hi bo") return "NOK: greeter-base"
    return "OK"
}

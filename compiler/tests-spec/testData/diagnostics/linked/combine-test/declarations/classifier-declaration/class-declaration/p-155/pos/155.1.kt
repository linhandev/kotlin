// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 155 -> sentence 155
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 155 -> sentence 155
 *                inheritance, overriding -> paragraph 155 -> sentence 155
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 155 -> sentence 155
 * NUMBER: 1
 * DESCRIPTION: subclass override of open superclass member combined with constructor delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val base: Int) {
    open fun f(): Int = base
}

class Child(base: Int, val extra: Int) : Base(base) {
    override fun f(): Int = base + extra
}

fun case1() {
    val c = Child(10, 5)
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.f() checkType { check<Int>() }
    val asBase: Base = c
    asBase.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Shape(val name: String) {
    open val area: Int get() = 0
}

class Square(val side: Int) : Shape("square") {
    override val area: Int get() = side * side
}

fun case2() {
    val s = Square(3)
    s checkType { check<Square>() }
    checkSubtype<Shape>(s)
    s.area checkType { check<Int>() }
    s.name checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class Greeter(val who: String) {
    open fun greet(): String = "hi $who"
}

class Loud(who: String) : Greeter(who) {
    override fun greet(): String = "HI $who!"
}

fun case3() {
    val g: Greeter = Loud("ann")
    g checkType { check<Greeter>() }
    g.greet() checkType { check<String>() }
    Loud("bo") checkType { check<Loud>() }
    checkSubtype<Greeter>(Loud("bo"))
}

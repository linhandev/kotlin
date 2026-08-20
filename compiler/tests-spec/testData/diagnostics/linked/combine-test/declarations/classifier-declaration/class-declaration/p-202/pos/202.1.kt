// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 202 -> sentence 202
 * PRIMARY LINKS: inheritance, overriding -> paragraph 202 -> sentence 202
 *                inheritance, inheriting -> paragraph 202 -> sentence 202
 * NUMBER: 1
 * DESCRIPTION: type inference for dynamic dispatch of overrides through superclass-typed references in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): String = "B"
}

class Child : Base() {
    override fun f(): String = "C"
}

fun dispatch(b: Base = Child()): String = b.f()

fun case1() {
    val child = Child()
    child checkType { check<Child>() }
    checkSubtype<Base>(child)
    child.f() checkType { check<String>() }
    dispatch(child) checkType { check<String>() }

    val asBase: Base = child
    asBase.f() checkType { check<String>() }
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
    fun banner(): String = "#$reading"
}

class ScaledMeter : Meter() {
    override val reading: Int = 2
}

fun case2() {
    val m = ScaledMeter()
    m checkType { check<ScaledMeter>() }
    checkSubtype<Meter>(m)
    m.reading checkType { check<Int>() }
    m.banner() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
    fun wrap(): String = "<${name()}>"
}

class LoudNamed : Named() {
    override fun name(): String = "loud"
}

fun case3() {
    val n = LoudNamed()
    n checkType { check<LoudNamed>() }
    checkSubtype<Named>(n)
    n.name() checkType { check<String>() }
    n.wrap() checkType { check<String>() }
}

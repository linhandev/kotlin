// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 202 -> sentence 202
 * PRIMARY LINKS: inheritance, overriding -> paragraph 202 -> sentence 202
 *                inheritance, inheriting -> paragraph 202 -> sentence 202
 * NUMBER: 1
 * DESCRIPTION: calls through a superclass-typed reference dynamically dispatch to the override implementation in a class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): String = "B"
}

class Child : Base() {
    override fun f(): String = "C"
}

fun dispatch(b: Base = Child()): String = b.f()

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
    fun banner(): String = "#$reading"
}

class ScaledMeter : Meter() {
    override val reading: Int = 2
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
    fun wrap(): String = "<${name()}>"
}

class LoudNamed : Named() {
    override fun name(): String = "loud"
}

fun box(): String {
    if (dispatch() != "C") return "NOK: default-arg"
    if (dispatch(Child()) != "C") return "NOK: child-arg"
    if (dispatch(Base()) != "B") return "NOK: base-arg"
    val asBase: Base = Child()
    if (asBase.f() != "C") return "NOK: as-base"
    if (Child().f() != "C") return "NOK: child-direct"
    if (Base().f() != "B") return "NOK: base-direct"

    if (Meter().banner() != "#1") return "NOK: meter"
    if (ScaledMeter().banner() != "#2") return "NOK: scaled"
    val asMeter: Meter = ScaledMeter()
    if (asMeter.banner() != "#2") return "NOK: meter-ref"
    if (asMeter.reading != 2) return "NOK: meter-reading"

    if (Named().wrap() != "<base>") return "NOK: named"
    if (LoudNamed().wrap() != "<loud>") return "NOK: loud"
    val asNamed: Named = LoudNamed()
    if (asNamed.wrap() != "<loud>") return "NOK: named-ref"
    if (asNamed.name() != "loud") return "NOK: named-name"
    return "OK"
}

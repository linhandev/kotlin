// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 176 -> sentence 176
 * PRIMARY LINKS: inheritance, overriding -> paragraph 176 -> sentence 176
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 176 -> sentence 176
 *                inheritance, inheriting -> paragraph 176 -> sentence 176
 * NUMBER: 1
 * DESCRIPTION: overriding an open member with the override modifier replaces the base implementation for the same input in a class declaration
 */

// TESTCASE NUMBER: 1
open class Identity {
    open fun compute(n: Int): Int = n
}

class Square : Identity() {
    override fun compute(n: Int): Int = n * n
}

// TESTCASE NUMBER: 2
open class PlainFormatter {
    open fun render(label: String): String = label
}

class BracketFormatter : PlainFormatter() {
    override fun render(label: String): String = "[$label]"
}

// TESTCASE NUMBER: 3
open class Counter {
    open val step: Int get() = 1
    fun advance(from: Int): Int = from + step
}

class DoubleStep : Counter() {
    override val step: Int get() = 2
}

fun box(): String {
    val id = Identity()
    val sq = Square()
    if (id.compute(4) != 4) return "NOK: base-compute"
    if (sq.compute(4) != 16) return "NOK: override-compute"
    if (id.compute(4) == sq.compute(4)) return "NOK: impl-not-replaced"

    val plain = PlainFormatter()
    val bracket = BracketFormatter()
    if (plain.render("x") != "x") return "NOK: base-render"
    if (bracket.render("x") != "[x]") return "NOK: override-render"

    if (Counter().advance(10) != 11) return "NOK: base-step"
    if (DoubleStep().advance(10) != 12) return "NOK: override-step"
    return "OK"
}

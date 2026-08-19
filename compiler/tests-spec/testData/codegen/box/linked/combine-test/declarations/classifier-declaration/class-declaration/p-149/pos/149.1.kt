// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 149 -> sentence 149
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 149 -> sentence 149
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 149 -> sentence 149
 * NUMBER: 1
 * DESCRIPTION: open class may appear as a class supertype in the supertype list of a class declaration
 */

// TESTCASE NUMBER: 1
open class Parent

class Child : Parent()

// TESTCASE NUMBER: 2
open class Named(val label: String)

class NamedChild(label: String) : Named(label)

// TESTCASE NUMBER: 3
open class Counter(val start: Int) {
    open fun next(): Int = start + 1
}

class Stepped(start: Int) : Counter(start) {
    override fun next(): Int = start + 10
}

fun viaChild(): Boolean = Child() is Parent

fun viaNamed(): String = NamedChild("ok").label

fun viaStepped(): Pair<Int, Int> {
    val s = Stepped(1)
    return s.start to s.next()
}

fun box(): String {
    if (!viaChild()) return "NOK: child-is-parent"
    if (Child() !is Child) return "NOK: child-type"
    if (viaNamed() != "ok") return "NOK: named"
    if (NamedChild("x").label != "x") return "NOK: named-x"
    if (viaStepped() != (1 to 11)) return "NOK: stepped"
    if (Stepped(5).next() != 15) return "NOK: stepped-5"
    return "OK"
}

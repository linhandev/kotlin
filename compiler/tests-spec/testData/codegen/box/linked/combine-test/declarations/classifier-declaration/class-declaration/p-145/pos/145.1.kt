// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 145 -> sentence 145
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 145 -> sentence 145
 *                inheritance, inheriting -> paragraph 145 -> sentence 145
 * NUMBER: 1
 * DESCRIPTION: subclass primary constructor delegates to superclass via : Parent(...) in the supertype list in class declaration
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)

class Child(x: Int) : Parent(x)

// TESTCASE NUMBER: 2
open class Base(val value: Int)

class Scaled(x: Int) : Base(x * 2)

// TESTCASE NUMBER: 3
open class NamedBase(val id: Int, val label: String)

class NamedChild(n: Int, tag: String) : NamedBase(n, tag)

fun viaChild(): Int = Child(2).x

fun viaScaled(): Int = Scaled(3).value

fun viaNamed(): Pair<Int, String> {
    val c = NamedChild(7, "ok")
    return c.id to c.label
}

fun box(): String {
    if (viaChild() != 2) return "NOK: child"
    if (Child(5).x != 5) return "NOK: child-5"
    if (viaScaled() != 6) return "NOK: scaled"
    if (Scaled(0).value != 0) return "NOK: scaled-zero"
    if (viaNamed() != (7 to "ok")) return "NOK: named"
    if (NamedChild(1, "a").id != 1 || NamedChild(1, "a").label != "a") return "NOK: named-direct"
    return "OK"
}

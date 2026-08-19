// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 146 -> sentence 146
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 146 -> sentence 146
 *                inheritance, inheriting -> paragraph 146 -> sentence 146
 * NUMBER: 1
 * DESCRIPTION: subclass without primary constructor parameters still must explicitly delegate via : Parent(...) in the supertype list in class declaration
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)

class Child : Parent(1)

// TESTCASE NUMBER: 2
open class Base(val value: Int)

class Fixed : Base(42)

// TESTCASE NUMBER: 3
open class Tagged(val code: Int, val mark: String)

class DefaultTagged : Tagged("hi".length, "d")

fun viaChild(): Int = Child().x

fun viaFixed(): Int = Fixed().value

fun viaDefault(): Pair<Int, String> {
    val t = DefaultTagged()
    return t.code to t.mark
}

fun box(): String {
    if (viaChild() != 1) return "NOK: child"
    if (Child().x != 1) return "NOK: child-again"
    if (viaFixed() != 42) return "NOK: fixed"
    if (viaDefault() != (2 to "d")) return "NOK: default"
    return "OK"
}

// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 87 -> sentence 87
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 87 -> sentence 87
 * NUMBER: 1
 * DESCRIPTION: default equals is referential so Child and Parent instances differ
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)
class Child(x: Int) : Parent(x)

fun test(): Boolean = Child(42) == Parent(42)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}

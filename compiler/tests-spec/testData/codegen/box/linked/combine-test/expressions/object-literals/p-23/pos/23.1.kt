
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: object declaration singleton differs from anonymous object literal instances
 */

// TESTCASE NUMBER: 1
open class Base

object O : Base()

fun test(): Boolean {
    val a: Any = object : Base() {}
    val b: Any = object : Base() {}
    return O === O && a !== b && a !== O
}

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}

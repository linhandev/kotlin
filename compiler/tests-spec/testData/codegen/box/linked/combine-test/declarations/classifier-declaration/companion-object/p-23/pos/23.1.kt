/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: nested class in companion is constructed via Outer.Companion.Inner
 */

// TESTCASE NUMBER: 1
class Outer {
    companion object {
        class Inner(val x: Int)
    }
}

fun test() = Outer.Companion.Inner(42).x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

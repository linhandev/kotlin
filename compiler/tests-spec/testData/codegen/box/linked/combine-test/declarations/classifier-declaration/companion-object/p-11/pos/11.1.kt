/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: extension property on Companion can be accessed via ClassName
 */

// TESTCASE NUMBER: 1
class Box {
    companion object
}

val Box.Companion.value get() = 42

fun test() = Box.value

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: extension function on Companion can be called via ClassName
 */

// TESTCASE NUMBER: 1
class Box {
    companion object
}

fun Box.Companion.ext() = "ext"

fun test() = Box.ext()

fun box(): String {
    if (test() != "ext") return "NOK"
    return "OK"
}

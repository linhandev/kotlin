/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: companion object val can be accessed via ClassName.property
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
    }
}

fun test() = Box.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

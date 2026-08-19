// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: run on companion object returns a property
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
    }
}

fun test() = Box.run { x }

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

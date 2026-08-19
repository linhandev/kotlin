// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: apply on companion object expression sets a var
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        var x = 0
    }
}

fun test() = Box.apply { x = 42 }.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

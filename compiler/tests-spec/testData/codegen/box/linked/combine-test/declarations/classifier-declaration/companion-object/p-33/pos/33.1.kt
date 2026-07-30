// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: let on companion property doubles the value
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
    }
}

fun test() = Box.x.let { it * 2 }

fun box(): String {
    if (test() != 84) return "NOK"
    return "OK"
}

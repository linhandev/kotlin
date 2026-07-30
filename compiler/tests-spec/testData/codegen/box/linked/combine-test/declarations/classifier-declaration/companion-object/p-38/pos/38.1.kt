// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: with(companion) can call companion members
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
        fun readX() = x
    }
}

fun test() = with(Box) { readX() }

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

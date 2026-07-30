/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: companion object lambda property can be invoked
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val processor: (Int) -> Int = { it * 2 }
    }
}

fun test() = Box.processor(5)

fun box(): String {
    if (test() != 10) return "NOK"
    return "OK"
}

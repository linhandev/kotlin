/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: companion object function with arguments can be called
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun add(a: Int, b: Int) = a + b
    }
}

fun test() = Box.add(1, 2)

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}

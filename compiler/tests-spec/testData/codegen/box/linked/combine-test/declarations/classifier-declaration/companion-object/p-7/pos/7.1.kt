/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: instance method can access companion object members
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
    }
    fun getX() = x
}

fun test() = Box().getX()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

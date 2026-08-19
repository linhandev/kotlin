/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: elvis on null companion property yields default
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x: String? = null
    }
}

fun test() = Box.x ?: "default"

fun box(): String {
    if (test() != "default") return "NOK"
    return "OK"
}

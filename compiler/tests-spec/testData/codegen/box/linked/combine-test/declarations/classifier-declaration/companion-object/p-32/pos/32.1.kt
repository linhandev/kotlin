/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: elvis on non-null companion property yields the value
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x: String? = "hello"
    }
}

fun test() = Box.x ?: "default"

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}

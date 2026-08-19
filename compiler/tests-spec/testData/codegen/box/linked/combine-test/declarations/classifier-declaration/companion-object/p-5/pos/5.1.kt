/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: companion object property initializes with a string value
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val name = "Box"
    }
}

fun test() = Box.name

fun box(): String {
    if (test() != "Box") return "NOK"
    return "OK"
}

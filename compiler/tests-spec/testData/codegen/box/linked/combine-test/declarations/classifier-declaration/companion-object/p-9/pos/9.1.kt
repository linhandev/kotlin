/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: companion object invoke(String) is called when no matching constructor exists
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        operator fun invoke(name: String) = "Hello $name"
    }
}

fun test() = Box("World")

fun box(): String {
    if (test() != "Hello World") return "NOK"
    return "OK"
}

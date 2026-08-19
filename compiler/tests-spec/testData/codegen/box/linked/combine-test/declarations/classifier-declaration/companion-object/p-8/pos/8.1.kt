/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: companion object invoke() is called when ClassName() has private constructor
 */

// TESTCASE NUMBER: 1
class Box private constructor() {
    companion object {
        operator fun invoke() = "called"
    }
}

fun test() = Box()

fun box(): String {
    if (test() != "called") return "NOK"
    return "OK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: companion object property type is inferred as String
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val value = "value"
    }
}

fun test(): String = Box.value

fun box(): String {
    if (test() != "value") return "NOK"
    return "OK"
}

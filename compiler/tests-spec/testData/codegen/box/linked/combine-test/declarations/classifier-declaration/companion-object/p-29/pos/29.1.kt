/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: companion object function return type is inferred as String
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun getValue() = "value"
    }
}

fun test(): String = Box.getValue()

fun box(): String {
    if (test() != "value") return "NOK"
    return "OK"
}

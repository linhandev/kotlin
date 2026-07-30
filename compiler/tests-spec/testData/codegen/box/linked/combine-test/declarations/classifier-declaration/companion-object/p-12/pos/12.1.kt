/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: named companion object members are accessed via ClassName.Name.member
 */

// TESTCASE NUMBER: 1
class Box {
    companion object Factory {
        val x = 42
    }
}

fun test() = Box.Factory.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

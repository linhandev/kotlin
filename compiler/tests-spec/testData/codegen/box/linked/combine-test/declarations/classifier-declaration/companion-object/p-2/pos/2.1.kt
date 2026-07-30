/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: companion object var can be assigned and read via ClassName.property
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        var x = 0
    }
}

fun test(): Int {
    Box.x = 42
    return Box.x
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

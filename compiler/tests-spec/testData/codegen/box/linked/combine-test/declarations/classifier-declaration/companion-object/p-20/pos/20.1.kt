// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: companion object property can be annotated with @JvmField
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        @JvmField
        val x = 42
    }
}

fun test() = Box.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}

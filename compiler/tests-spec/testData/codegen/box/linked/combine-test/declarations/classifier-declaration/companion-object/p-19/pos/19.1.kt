// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: companion object function can be annotated with @JvmStatic
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        @JvmStatic
        fun foo() = "static"
    }
}

fun test() = Box.foo()

fun box(): String {
    if (test() != "static") return "NOK"
    return "OK"
}

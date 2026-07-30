/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: companion object function can be called via ClassName.fun()
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun foo() = "hello"
    }
}

fun test() = Box.foo()

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}

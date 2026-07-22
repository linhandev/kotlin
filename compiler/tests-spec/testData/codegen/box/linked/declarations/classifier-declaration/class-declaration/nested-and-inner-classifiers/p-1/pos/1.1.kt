// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: nested class can be instantiated without outer instance
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested(val value: Int)
}

fun box(): String {
    val nested = Outer.Nested(42)
    return if (nested.value == 42) "OK" else "NOK"
}

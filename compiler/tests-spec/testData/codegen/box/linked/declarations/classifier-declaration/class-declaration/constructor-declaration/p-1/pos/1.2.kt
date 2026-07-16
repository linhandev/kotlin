// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: class without explicit constructor uses default constructor at runtime
 */

// TESTCASE NUMBER: 1
class Empty

fun box(): String {
    val e = Empty()
    return if (e is Empty) "OK" else "NOK"
}

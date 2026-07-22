// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class with primary constructor property is instantiated and read at runtime
 */

// TESTCASE NUMBER: 1
class Foo(val value: Int)

fun box(): String {
    val foo = Foo(21)
    return if (foo.value == 21) "OK" else "NOK"
}

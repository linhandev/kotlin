// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: same implicit receiver serves as dispatch and extension receiver; with provides dispatch for external extension call
 */

interface Y1105

class X1105 : Y1105 {
    fun Y1105.foo(): String = "OK"

    fun bar(): String = foo()
}

// TESTCASE NUMBER: 1
fun box(): String {
    val x: X1105 = X1105()
    val y: Y1105 = x
    val fromBar = x.bar()
    val fromWith = with(x) { y.foo() }
    return if (fromBar == "OK" && fromWith == "OK") "OK" else "NOK: $fromBar/$fromWith"
}

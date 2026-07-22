// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class implements interface at runtime
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): Int
}

class C : I {
    override fun foo(): Int = 42
}

fun box(): String {
    val c: I = C()
    return if (c.foo() == 42) "OK" else "NOK"
}

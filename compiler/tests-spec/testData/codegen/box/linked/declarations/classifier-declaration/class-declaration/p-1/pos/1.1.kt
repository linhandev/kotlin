// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class with multiple interfaces dispatches calls at runtime
 */

// TESTCASE NUMBER: 1
interface A {
    fun a(): Int
}

interface B {
    fun b(): Int
}

class C : A, B {
    override fun a(): Int = 1
    override fun b(): Int = 2
}

fun box(): String {
    val c: C = C()
    return if (c.a() == 1 && c.b() == 2) "OK" else "NOK"
}

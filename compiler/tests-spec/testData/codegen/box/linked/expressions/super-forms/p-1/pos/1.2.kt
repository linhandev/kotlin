// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, super-forms -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: super<C>.foo() returns "C"; super<B>.foo() returns "B" in class D
 */

// TESTCASE NUMBER: 1

interface A {
    fun foo(): String = "A"
}

interface B {
    fun foo(): String = "B"
}

open class C : A {
    override fun foo(): String = "C"
}

class D : C(), B {
    fun read(): String {
        val fromC = super<C>.foo()
        val fromB = super<B>.foo()
        return if (fromC == "C" && fromB == "B") "OK" else "NOK"
    }

    override fun foo(): String = "D"
}

fun box(): String {
    return D().read()
}

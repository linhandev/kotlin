// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, super-forms -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: super<Klazz>@type in inner class accesses outer classifier supertype
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
    inner class Inner {
        fun read(): String {
            val fromC = super<C>@D.foo()
            val fromB = super<B>@D.foo()
            return if (fromC == "C" && fromB == "B") "OK" else "NOK"
        }
    }

    override fun foo(): String = "D"
}

fun box(): String {
    return D().Inner().read()
}

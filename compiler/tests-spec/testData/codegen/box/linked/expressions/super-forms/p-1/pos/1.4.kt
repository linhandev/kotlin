// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, super-forms -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: super<Klazz> with interface immediate supertype
 */

// TESTCASE NUMBER: 1

interface T {
    fun foo(): String = "T"
}

open class C

class A : C(), T {
    fun read(): String = super<T>.foo()
}

fun box(): String {
    return if (A().read() == "T") "OK" else "NOK"
}

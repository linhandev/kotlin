// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: when on sealed class matches nested sealed subtypes A.B and A.B.C with is branches
 */

// TESTCASE NUMBER: 1

sealed class A {
    class B : A() {
        class C : A()
    }
}

class D : A()

fun box(): String {
    val s: A = D()
    return when (s) {
        is A.B -> "B"
        is A.B.C -> "C"
        is D -> "OK"
    }
}

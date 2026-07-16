// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: sealed subtype covered by negated type test condition !is Sj
 */

// TESTCASE NUMBER: 1

sealed class Sealed(val x: Int) {
    object First : Sealed(1)
    class NonFirst(x: Int) : Sealed(x)
}

fun box(): String {
    val s: Sealed = Sealed.First
    val r = when (s) {
        is Sealed.First -> 1
        !is Sealed.First -> 0
    }
    return if (r == 1) "OK" else "NOK"
}

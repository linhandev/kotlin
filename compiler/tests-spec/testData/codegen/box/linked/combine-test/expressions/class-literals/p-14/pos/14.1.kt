// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: inner class class literal differs from outer class class literal, verifying runtime semantics
 */

class Outer { inner class Inner }

// TESTCASE NUMBER: 1
fun test(): Boolean = Outer::class != Outer.Inner::class

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: object can implement multiple interfaces
 */

// TESTCASE NUMBER: 1
interface A {
    fun a(): Int
}

interface B {
    fun b(): Int
}

object S : A, B {
    override fun a(): Int = 1
    override fun b(): Int = 2
}

fun test(): Int = S.a() + S.b()

fun box(): String {
    if (test() != 3) return "NOK: test"
    val a: A = S
    val b: B = S
    if (a.a() != 1) return "NOK: a"
    if (b.b() != 2) return "NOK: b"
    return "OK"
}

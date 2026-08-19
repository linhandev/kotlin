
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: object literal can implement multiple interfaces
 */

// TESTCASE NUMBER: 1
interface A { fun a(): Int }
interface B { fun b(): Int }

fun test(): Int {
    val o = object : A, B {
        override fun a(): Int = 1
        override fun b(): Int = 2
    }
    return o.a() + o.b()
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}

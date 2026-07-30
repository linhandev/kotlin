// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: object can implement multiple interfaces
 * HELPERS: checkType
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

fun case_1() {
    checkSubtype<A>(S)
    checkSubtype<B>(S)
    checkSubtype<Int>(S.a())
    checkSubtype<Int>(S.b())
}

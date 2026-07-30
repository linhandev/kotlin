// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: object literal can implement multiple interfaces
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface A { fun a(): Int }
interface B { fun b(): Int }

fun case_1(): Int {
    val o = object : A, B {
        override fun a(): Int = 1
        override fun b(): Int = 2
    }
    return o.a() + o.b()
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 27 -> sentence 27
 *                declarations, declarations-with-type-parameters -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: object can implement a covariant generic interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Producer<out T> {
    fun produce(): T
}

object IntProd : Producer<Int> {
    override fun produce(): Int = 1
}

fun case_1() {
    checkSubtype<Producer<Number>>(IntProd)
}

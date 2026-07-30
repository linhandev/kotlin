// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 47 -> sentence 47
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: enum class as upper bound
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A }

class EnumBox<T : E>(val e: T)

fun test(): E = EnumBox(E.A).e

fun case1() {
    checkSubtype<E>(test())
}

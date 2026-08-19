// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 20 -> sentence 20
 *                declarations, declarations-with-type-parameters -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: generic data class component1 preserves type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Box<T>(val v: T)

fun case_1() {
    val (x) = Box("a")
    checkSubtype<String>(x)
}

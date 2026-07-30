// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 16 -> sentence 16
 *                declarations, destructuring-declarations -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: var primary constructor property still generates componentN
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Counter(var n: Int)

fun case_1() {
    val (v) = Counter(1)
    checkSubtype<Int>(v)
}

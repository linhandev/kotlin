// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 315 -> sentence 315
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 315 -> sentence 315
 * NUMBER: 1
 * DESCRIPTION: precise types for local class in a function is not a member nested classifier
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    fun local(): Int {
        class Local(val v: Int)
        return Local(1).v
    }
    local() checkType { check<Int>() }
    checkSubtype<Int>(local())
}

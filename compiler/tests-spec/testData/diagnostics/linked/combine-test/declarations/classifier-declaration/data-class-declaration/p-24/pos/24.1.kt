// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: manually overridden equals still compiles for data class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Wrap(val v: Int) {
    override fun equals(other: Any?): Boolean = other is Wrap && v == <!DEBUG_INFO_SMARTCAST!>other<!>.v
}

fun case_1() {
    checkSubtype<Boolean>(Wrap(1) == Wrap(1))
}

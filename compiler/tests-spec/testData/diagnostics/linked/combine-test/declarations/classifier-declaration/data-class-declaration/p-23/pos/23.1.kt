// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: custom toString overrides the generated implementation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Id(val v: Int) {
    override fun toString(): String = "#$v"
}

fun case_1() {
    checkSubtype<String>(Id(1).toString())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: body properties are not part of equals/copy/componentN
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Box(val id: Int) {
    var extra = 0
}

fun case_1() {
    checkSubtype<Boolean>(Box(1).copy() == Box(1))
}

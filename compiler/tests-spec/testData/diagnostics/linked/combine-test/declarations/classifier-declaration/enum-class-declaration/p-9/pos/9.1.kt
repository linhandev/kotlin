// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: subject-less when can compare enum values with ==
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { ON, OFF }

fun case_1(e: E) {
    checkSubtype<String>(when {
        e == E.ON -> "on"
        e == E.OFF -> "off"
        else -> "x"
    })
}

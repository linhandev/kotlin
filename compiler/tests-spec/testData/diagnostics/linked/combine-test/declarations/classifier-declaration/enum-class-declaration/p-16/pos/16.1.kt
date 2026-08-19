// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 16 -> sentence 16
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: exhaustive when result can be assigned to a val
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { X, Y }

fun case_1(e: E) {
    val s = when (e) {
        E.X -> "x"
        E.Y -> "y"
    }
    checkSubtype<String>(s)
}

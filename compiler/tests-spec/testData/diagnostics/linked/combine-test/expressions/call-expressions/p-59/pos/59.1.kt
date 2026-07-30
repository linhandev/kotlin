// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 59 -> sentence 59
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 59 -> sentence 59
 *                type-inference, introduction-1 -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: SAM-like function type with generic trailing lambda infers type argument correctly
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> apply(x: T, block: (T) -> T): T = block(x)

fun case_1() {
    checkSubtype<Int>(apply(1) { it + 1 })
}

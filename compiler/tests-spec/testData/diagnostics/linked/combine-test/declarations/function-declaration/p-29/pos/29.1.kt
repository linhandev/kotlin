// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 29 -> sentence 29
 *                expressions, call-expressions -> paragraph 29 -> sentence 29
 *                type-system, introduction-1 -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: explicit null overrides nullable default parameter in top-level function call type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(x: String? = "d"): String? = x

// TESTCASE NUMBER: 1
fun test(): String? = f(null)

fun case1() {
    checkSubtype<String?>(test())
}

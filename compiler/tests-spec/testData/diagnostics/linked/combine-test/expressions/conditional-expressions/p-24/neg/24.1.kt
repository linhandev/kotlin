// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: conditional expression with nullable branch cannot be assigned to non-nullable type
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): String = if (flag) <!TYPE_MISMATCH!>x<!> else "default"

// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: nullable try expression cannot be assigned to non-nullable String
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = <!RETURN_TYPE_MISMATCH!>try { x } catch (e: Exception) { null }<!>

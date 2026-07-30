// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: try expression with Int and String branches cannot be assigned to Int
 */

// TESTCASE NUMBER: 1
fun test(): Int = try { 1 } catch (e: Exception) <!TYPE_MISMATCH!>{ "error" }<!>

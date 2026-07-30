// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                declarations, declarations-with-type-parameters -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: star-projected type List<*>::class is not a valid class literal, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun case1() = <!CLASS_LITERAL_LHS_NOT_A_CLASS!>List<*>::class<!>

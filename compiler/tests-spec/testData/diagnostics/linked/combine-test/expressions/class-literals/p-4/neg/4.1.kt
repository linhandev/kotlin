// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: ::class cannot be used on null literal, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun case1() = <!EXPRESSION_OF_NULLABLE_TYPE_IN_CLASS_LITERAL_LHS!>null<!>::class

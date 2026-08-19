// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: nullable type String?::class is not a valid class literal syntax, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun case1() = <!NULLABLE_TYPE_IN_CLASS_LITERAL_LHS!>String?::class<!>

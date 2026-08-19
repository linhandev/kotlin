// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 25 -> sentence 25
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: callable reference String::length of type (String) -> Int cannot be assigned to (Int) -> String, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
val bad: (Int) -> String = <!TYPE_MISMATCH!>String::<!TYPE_MISMATCH!>length<!><!>

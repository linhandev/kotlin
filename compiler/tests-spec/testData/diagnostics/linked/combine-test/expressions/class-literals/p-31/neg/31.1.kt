// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: function type class literal ((Int) -> Int)::class is not equal to Function1::class with class literal comparison, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun case1(): Boolean = ((<!TYPE_MISMATCH!>Int<!>)<!SYNTAX!><!> <!SYNTAX!>-><!> <!SYNTAX!>Int<!><!SYNTAX!>)<!><!SYNTAX!>::<!>class <!SYNTAX!>==<!> <!SYNTAX!>Function1<!><!SYNTAX!>::<!>class<!SYNTAX!><!>

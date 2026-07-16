// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: anonymous object with multiple supertypes escaping scope requires explicit cast
 */

// TESTCASE NUMBER: 1
open class Base
interface I1
interface I2

fun <!AMBIGUOUS_ANONYMOUS_TYPE_INFERRED!>case1<!>() = object : Base(), I1, I2 {}

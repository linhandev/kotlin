// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlinFile importList before packageHeader violates order
 */

// TESTCASE NUMBER: 1
import kotlin.math.abs

<!SYNTAX!>package<!> <!SYNTAX!>tokens<!><!SYNTAX!>.<!><!SYNTAX!>spec<!><!SYNTAX!>.<!><!SYNTAX!>p1<!>

fun case1(): String = if (abs(-1) == 1) "OK" else "NOK"

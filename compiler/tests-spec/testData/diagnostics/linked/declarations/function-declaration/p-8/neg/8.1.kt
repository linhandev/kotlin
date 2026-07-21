// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: named argument followed by positional argument in call
 */

// TESTCASE NUMBER: 1
fun foo(a: Int, b: String) {}

fun namedThenPositional() {
    foo(b = "x", <!MIXING_NAMED_AND_POSITIONED_ARGUMENTS!>1<!>)
}

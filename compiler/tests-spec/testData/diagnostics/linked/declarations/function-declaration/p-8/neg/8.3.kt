// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 3
 * DESCRIPTION: too many arguments in call
 */

// TESTCASE NUMBER: 1
fun tooMany(a: Int, b: String) {}

fun excessArgs() {
    tooMany(1, "x", <!TOO_MANY_ARGUMENTS!>""<!>)
}

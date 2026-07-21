// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: named argument followed by positional argument
 */

// TESTCASE NUMBER: 1
fun bar(x: Int = 1, y: Int = 1, z: String) {}

fun mixAfterNamed() {
    bar(z = "", <!MIXING_NAMED_AND_POSITIONED_ARGUMENTS!>1<!>)
}

// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, spread-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: bar(*arrayOf("a")) spread on non-vararg Array parameter reports NON_VARARG_SPREAD_ERROR
 */

fun bar(x: Array<String>) {}

// TESTCASE NUMBER: 1
fun case1() {
    bar(<!NON_VARARG_SPREAD_ERROR!>*<!>arrayOf("a"))
}

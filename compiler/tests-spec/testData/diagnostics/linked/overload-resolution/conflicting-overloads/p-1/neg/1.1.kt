// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, conflicting-overloads -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: interlinked member overloads with same phantom applicability report CONFLICTING_OVERLOADS
 */

// TESTCASE NUMBER: 1
class Holder118N {
    <!CONFLICTING_OVERLOADS!>fun snap118N(a: Int): Int<!> = a
    <!CONFLICTING_OVERLOADS!>fun snap118N(a: Int): String<!> = a.toString()
}

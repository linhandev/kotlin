// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: tailrec modifier on a non-tail-recursive function produces NO_TAIL_CALLS_FOUND
 * UNEXPECTED BEHAVIOUR
 */

// TESTCASE NUMBER: 1
<!NO_TAIL_CALLS_FOUND!>tailrec<!> fun factorial(i: Int): Int {
    if (i == 0) return 1
    return i * <!NON_TAIL_RECURSIVE_CALL!>factorial<!>(i - 1)
}

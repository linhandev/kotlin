// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: five-step lambda statement processing — step 3 Important phantom it does not override zero-parameter choice
 * HELPERS: checkType
 */

fun takeZero1432(f: () -> Unit) {}

// TESTCASE NUMBER: 1
fun case_1() {
    takeZero1432 { <!UNRESOLVED_REFERENCE!>it<!> }
}

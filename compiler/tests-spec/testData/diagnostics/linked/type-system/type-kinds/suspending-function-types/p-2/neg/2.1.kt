// FIR_IDENTICAL
// LANGUAGE: -SuspendConversion
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, suspending-function-types -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Suspending and non-suspending function types are unrelated by subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val fooLambda: (Int) -> String = { it.toString() }
    val error: suspend (Int) -> String = <!TYPE_MISMATCH!>fooLambda<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val suspendFooLambda: suspend (Int) -> String = { it.toString() }
    val error: (Int) -> String = <!TYPE_MISMATCH!>suspendFooLambda<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: () -> Unit = { }
    val error: suspend () -> Unit = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val f: suspend () -> Unit = { }
    val error: () -> Unit = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 5
fun case_5(f: (Int) -> String) {
    val error: suspend (Int) -> String = <!TYPE_MISMATCH!>f<!>
}

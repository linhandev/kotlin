// FIR_IDENTICAL
// LANGUAGE: -SuspendConversion
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, suspending-function-types -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Non-suspending function values cannot be used where suspending function types are required
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): () -> Unit = { }

fun case_1_use() {
    val error: suspend () -> Unit = <!TYPE_MISMATCH!>case_1()<!>
}


// TESTCASE NUMBER: 2
fun case_2(): suspend () -> Unit = { }

fun case_2_use() {
    val error: () -> Unit = <!TYPE_MISMATCH!>case_2()<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: (Int) -> String = { x -> x.toString() }
    checkSubtype<suspend (Int) -> String>(<!TYPE_MISMATCH!>f<!>)
}


// TESTCASE NUMBER: 4
fun case_4(f: suspend (Int) -> String) {}

fun case_4_use() {
    val f: (Int) -> String = { x -> x.toString() }
    case_4(<!UNSUPPORTED_FEATURE!>f<!>)
}


// TESTCASE NUMBER: 5
fun case_5(): Int = 1

fun case_5_use() {
    val f: () -> Int = ::case_5
    val error: suspend () -> Int = <!TYPE_MISMATCH!>f<!>
}

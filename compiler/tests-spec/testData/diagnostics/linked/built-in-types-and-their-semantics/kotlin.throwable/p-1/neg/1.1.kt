// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.throwable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: only kotlin.Throwable subtypes may be thrown or used in catch clauses
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    throw <!TYPE_MISMATCH!>"error"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    try {
        throw Exception()
    } catch (<!TYPE_MISMATCH!>e: String<!>) {
    }
}


// TESTCASE NUMBER: 3
fun case_3() {
    val s: String = "x"
    val t: Throwable = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val i: Int = 1
    throw <!TYPE_MISMATCH!>i<!>
}

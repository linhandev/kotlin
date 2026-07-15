// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Throw expressions and non-terminating control flow have kotlin.Nothing type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun fail_1(): Nothing = throw Exception("1")

fun case_1() {
    fail_1()
    <!UNREACHABLE_CODE!>1<!>
}


// TESTCASE NUMBER: 2
fun case_2(): Int {
    throw IllegalStateException()
    <!UNREACHABLE_CODE!>return 1<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    throw Exception()
    <!UNREACHABLE_CODE!>Unit<!>
}


// TESTCASE NUMBER: 4
fun case_4(): Nothing {
    throw Exception()
}


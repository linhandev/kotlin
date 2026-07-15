// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.string -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.String plus operator and length property preserve kotlin.String typing
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    ("hello" + " world") checkType { check<String>() }
    ("a" + 'b') checkType { check<String>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    "kotlin".length checkType { check<Int>() }
    "".length checkType { check<Int>() }
}


// TESTCASE NUMBER: 3
fun case_3(s: String, t: String) {
    (s + t) checkType { check<String>() }
    s.length checkType { check<Int>() }
}

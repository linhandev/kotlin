// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.char -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Char inc, dec, plus and minus operators preserve expected result types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    ('a'.inc()) checkType { check<Char>() }
    ('b'.dec()) checkType { check<Char>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    ('a' + 1) checkType { check<Char>() }
    ('z' - 1) checkType { check<Char>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    ('a' - 'b') checkType { check<Int>() }
}


// TESTCASE NUMBER: 4
fun case_4(c: Char) {
    (c + 2) checkType { check<Char>() }
    (c - c) checkType { check<Int>() }
}

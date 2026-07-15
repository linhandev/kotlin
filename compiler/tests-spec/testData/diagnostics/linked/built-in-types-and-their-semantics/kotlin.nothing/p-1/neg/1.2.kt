// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Functions returning kotlin.Nothing must not return normally
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Nothing {
    <!RETURN_TYPE_MISMATCH!>return<!>
}


// TESTCASE NUMBER: 2
fun case_2(): Nothing {
    return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 3
fun case_3(): Nothing {
    return <!TYPE_MISMATCH!>"x"<!>
}


// TESTCASE NUMBER: 4
fun case_4(): Nothing {
    return <!TYPE_MISMATCH!>Unit<!>
}


// TESTCASE NUMBER: 5
fun case_5(): Nothing {
    if (true) return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>false<!>
    throw Exception()
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.unit -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Unit-returning functions cannot return values of other types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Unit {
    return <!TYPE_MISMATCH!>"ok"<!>
}


// TESTCASE NUMBER: 2
fun case_2(): Unit {
    return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>false<!>
}


// TESTCASE NUMBER: 3
fun case_3(): Unit {
    return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0<!>
}


// TESTCASE NUMBER: 4
class Case4 {
    fun process(): Unit {
        return <!NULL_FOR_NONNULL_TYPE!>null<!>
    }
}


// TESTCASE NUMBER: 5
fun case_5(): Unit {
    if (true) return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>42<!>
    Unit
}

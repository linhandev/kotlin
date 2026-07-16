// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.comparable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Comparable compareTo returns kotlin.Int for built-in comparable types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    1.compareTo(2) checkType { check<Int>() }
    1L.compareTo(2L) checkType { check<Int>() }
    1.0.compareTo(2.0) checkType { check<Int>() }
    'a'.compareTo('b') checkType { check<Int>() }
    "a".compareTo("b") checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun <T : Comparable<T>> case_2(x: T, y: T) {
    x.compareTo(y) checkType { check<Int>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<Comparable<Int>>(1)
    checkSubtype<Comparable<String>>("x")
}

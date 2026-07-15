// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, iterator-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Iterator provides hasNext and next with expected result types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val it: Iterator<Int> = arrayOf(1, 2).iterator()
    it.hasNext() checkType { check<Boolean>() }
    it.next() checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val it: Iterator<String> = listOf("a", "b").iterator()
    checkSubtype<Iterator<String>>(it)
}


// TESTCASE NUMBER: 3
fun case_3(it: Iterator<Double>) {
    it.hasNext() checkType { check<Boolean>() }
    it.next() checkType { check<Double>() }
}

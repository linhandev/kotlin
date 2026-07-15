// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, iterator-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Array iterator operator produces kotlin.Iterator for element type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val arr = arrayOf(1, 2, 3)
    val it = arr.iterator()
    checkSubtype<Iterator<Int>>(it)
}


// TESTCASE NUMBER: 2
fun case_2() {
    for (x in arrayOf("a", "b")) {
        x checkType { check<String>() }
    }
}

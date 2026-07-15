// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.comparable -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Comparable type parameter and nullable comparable references
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
enum class Size { S, M, L }
fun case_1(x: Comparable<Int>) {
    x.compareTo(1) checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val c: Comparable<String>? = null
    checkSubtype<Comparable<String>?>(c)
}


// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<Comparable<Size>>(Size.S)
    Size.S.compareTo(Size.M) checkType { check<Int>() }
}

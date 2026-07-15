// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.string -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.String literals, nullable variants and kotlin.Comparable subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val s: String = "hello"
    checkSubtype<Comparable<String>>(s)
    s.compareTo("world") checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val ns: String? = null
    checkSubtype<String?>(ns)
}


// TESTCASE NUMBER: 3
fun case_3(s: String) {
    checkSubtype<Comparable<String>>(s)
    s checkType { check<String>() }
}


// TESTCASE NUMBER: 4
fun case_4() {
    "" checkType { check<String>() }
    "kotlin" checkType { check<String>() }
}

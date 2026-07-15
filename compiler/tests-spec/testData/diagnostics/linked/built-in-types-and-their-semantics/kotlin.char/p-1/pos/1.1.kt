// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.char -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: constant-literals, character-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Char literals, nullable variants and kotlin.Comparable subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val c: Char = 'a'
    checkSubtype<Comparable<Char>>(c)
    c.compareTo('b') checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val nc: Char? = null
    checkSubtype<Char?>(nc)
}


// TESTCASE NUMBER: 3
fun case_3(c: Char) {
    checkSubtype<Comparable<Char>>(c)
    c checkType { check<Char>() }
}


// TESTCASE NUMBER: 4
fun case_4() {
    ' ' checkType { check<Char>() }
    '\uFFFF' checkType { check<Char>() }
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: data class destructuring via generated componentN functions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class DC(val x: Int, val y: String)

fun case1(d: DC) {
    val (a, b) = d
    a checkType { check<Int>() }
    b checkType { check<String>() }
}

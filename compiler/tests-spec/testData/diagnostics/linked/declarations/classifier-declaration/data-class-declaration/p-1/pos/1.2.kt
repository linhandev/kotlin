// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: data class generated copy function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class DC(val x: Int, val y: Double)

fun case1(d: DC) {
    val copied = d.copy(x = 2)
    copied checkType { check<DC>() }
    copied.y checkType { check<Double>() }
}

// DIAGNOSTICS: -UNREACHABLE_CODE -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, local-property-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS:  declarations, property-declaration, property-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: uninitialized local properties used after catch block with control-flow exception (unexpected behaviour)
 */

/*
 * TESTCASE NUMBER: 1
 * UNEXPECTED BEHAVIOUR
 * ISSUES: KT-35565
 */
fun case_1() {
    val x1: String
    val x: Boolean
    try {
        val x0: Boolean = (throw Exception()) || true
        !x // UNINITIALIZED_VARIABLE should be
        val a: Int = x1.toInt() // UNINITIALIZED_VARIABLE should be
    } catch (e: Exception) {
    }
}

/*
 * TESTCASE NUMBER: 2
 * UNEXPECTED BEHAVIOUR
 * ISSUES: KT-35565
 */
fun case_2() {
    val x: Boolean = false
    try {
        x = (throw Exception()) || true // VAL_REASSIGNMENT should be
    } catch (e: Exception) {
    }
}

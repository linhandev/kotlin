/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, finding-optimal-constraint-system-solution -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: push-down constraint on LUB result infers Number for if (flag) a else b branches
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val flag = true
    val result: Number = if (flag) 1 else 2.0
    return if (result.toDouble() == 1.0) "OK" else "NOK"
}

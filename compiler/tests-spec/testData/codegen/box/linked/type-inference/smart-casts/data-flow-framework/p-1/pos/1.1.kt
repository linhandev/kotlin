/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, data-flow-framework -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: x = y assignment transfer propagates rhs smart cast type to lhs without explicit cast
 */
// TESTCASE NUMBER: 1

fun assign1411(): Int {
    var x: Any? = null
    val y = 42
    x = y
    return x + 1
}

fun box(): String = if (assign1411() == 43) "OK" else "NOK"

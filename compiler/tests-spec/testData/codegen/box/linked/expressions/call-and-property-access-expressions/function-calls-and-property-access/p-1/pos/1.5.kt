// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: applyTo(3) { it * 2 } passes trailing lambda as last argument
 */

// TESTCASE NUMBER: 1

fun applyTo(value: Int, block: (Int) -> Int): Int = block(value)

fun box(): String {
    if (applyTo(3) { it * 2 } != 6) return "NOK"
    return "OK"
}

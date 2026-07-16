// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Box(4).n reads property n on Box instance
 */

// TESTCASE NUMBER: 1

class Box(val n: Int)

fun box(): String {
    if (Box(4).n != 4) return "NOK"
    return "OK"
}

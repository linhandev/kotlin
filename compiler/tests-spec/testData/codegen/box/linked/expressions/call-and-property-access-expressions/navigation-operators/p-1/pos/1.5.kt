// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: Int.MAX_VALUE and kotlin.math.abs(-3) resolve via qualified dot access
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (Int.MAX_VALUE <= 0) return "NOK"
    if (kotlin.math.abs(-3) != 3) return "NOK"
    return "OK"
}

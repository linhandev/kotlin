// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: safe navigation returns null for null receiver and delegates otherwise
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val nullStr: String? = null
    if (nullStr?.length != null) return "NOK"
    val text: String? = "ab"
    if (text?.length != 2) return "NOK"
    return "OK"
}

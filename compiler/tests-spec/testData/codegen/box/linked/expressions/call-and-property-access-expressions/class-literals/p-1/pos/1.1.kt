// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, class-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: String::class.simpleName is "String"
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if (String::class.simpleName == "String") "OK" else "NOK"
}

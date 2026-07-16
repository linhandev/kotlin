// LANGUAGE: +DataObjects
// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: data object singleton is accessible and comparable at runtime
 */

// TESTCASE NUMBER: 1
data object Token

fun box(): String {
    val a: Token = Token
    val b: Token = Token
    return if (a === b && a.toString().contains("Token")) "OK" else "NOK"
}

// LANGUAGE: +InlineClasses
// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: legacy inline class works at runtime
 */

// TESTCASE NUMBER: 1
inline class Legacy(val x: Int)

fun box(): String {
    val v = Legacy(21)
    return if (v.x == 21) "OK" else "NOK"
}

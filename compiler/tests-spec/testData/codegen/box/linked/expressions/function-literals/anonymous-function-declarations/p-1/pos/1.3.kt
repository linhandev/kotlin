// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: anonymous extension function can be called on receiver type
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val len: String.() -> Int = fun String.(): Int = length
    if (len("abc") != 3) return "NOK"
    return "OK"
}

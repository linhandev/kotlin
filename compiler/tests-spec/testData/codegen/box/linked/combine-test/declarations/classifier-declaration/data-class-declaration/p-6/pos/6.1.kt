// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: destructuring fewer components than parameters is allowed
 */

// TESTCASE NUMBER: 1
data class Rgb(val r: Int, val g: Int, val b: Int)

fun test(c: Rgb): Int {
    val (r, g) = c
    return r + g
}

fun box(): String {
    if (test(Rgb(1, 2, 3)) != 3) return "NOK"
    return "OK"
}

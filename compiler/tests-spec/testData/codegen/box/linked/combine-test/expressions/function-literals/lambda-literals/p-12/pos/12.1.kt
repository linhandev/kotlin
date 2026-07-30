// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: fewer destructuring bindings than available components is allowed
 */

// TESTCASE NUMBER: 1
data class Rgb(val r: Int, val g: Int, val b: Int)

fun test(c: Rgb): Int = c.let { (r, g) -> r + g }

fun box(): String {
    if (test(Rgb(1, 2, 3)) != 3) return "NOK"
    return "OK"
}

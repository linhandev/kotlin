// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Triple destructures into three bindings in a lambda
 */

// TESTCASE NUMBER: 1
fun test(t: Triple<Int, Int, Int>): Int = t.let { (a, b, c) -> a + b + c }

fun box(): String {
    if (test(Triple(1, 2, 3)) != 6) return "NOK"
    return "OK"
}

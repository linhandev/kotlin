// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 18 -> sentence 18
 *                type-system, nullable-types -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: nullable Pair as whole argument can be destructured via safe call
 */

// TESTCASE NUMBER: 1
fun test(p: Pair<Int?, String?>?): Int? =
    p?.let { (a, b) -> (a ?: 0) + (b?.length ?: 0) }

fun box(): String {
    if (test(1 to "ab") != 3) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}

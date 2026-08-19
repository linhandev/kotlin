// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: fold with non-destructured accumulator and destructured Pair
 */

// TESTCASE NUMBER: 1
fun test(ps: List<Pair<Int, Int>>): Int =
    ps.fold(0) { sum, (x, y) -> sum + x * y }

fun box(): String {
    if (test(listOf(2 to 3, 4 to 5)) != 26) return "NOK"
    return "OK"
}

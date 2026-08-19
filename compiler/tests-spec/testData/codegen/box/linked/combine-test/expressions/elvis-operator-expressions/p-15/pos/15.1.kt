// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 15 -> sentence 15
 *                expressions, function-literals, lambda-literals -> paragraph 15 -> sentence 15
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand labeled return skips null elements in forEach lambda
 */

// TESTCASE NUMBER: 1
fun test(xs: List<String?>): List<Int> {
    val result = mutableListOf<Int>()
    xs.forEach {
        val n = it?.length ?: return@forEach
        result.add(n)
    }
    return result
}

fun box(): String {
    val actual = test(listOf("a", null, "bc"))
    if (actual != listOf(1, 2)) return "NOK"
    return "OK"
}

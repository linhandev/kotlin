// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 28 -> sentence 28
 *                expressions, jump-expressions, return-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: inline higher-order allows non-local return from destructuring lambda
 */

// TESTCASE NUMBER: 1
inline fun <T> each(xs: List<T>, block: (T) -> Unit) {
    xs.forEach(block)
}

fun test(ps: List<Pair<Int, Int>>): Int? {
    each(ps) { (a, _) ->
        if (a == 0) return null
    }
    return 1
}

fun box(): String {
    if (test(listOf(1 to 2, 3 to 4)) != 1) return "NOK"
    if (test(listOf(0 to 1)) != null) return "NOK"
    return "OK"
}

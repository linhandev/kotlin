// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 28 -> sentence 28
 *                expressions, jump-expressions, return-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: inline higher-order allows non-local return from destructuring lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun <T> each(xs: List<T>, block: (T) -> Unit) {
    xs.forEach(block)
}

fun case_1(ps: List<Pair<Int, Int>>) {
    fun run(): Int? {
        each(ps) { (a, _) ->
            if (a == 0) return null
        }
        return 1
    }
    checkSubtype<Int?>(run())
}

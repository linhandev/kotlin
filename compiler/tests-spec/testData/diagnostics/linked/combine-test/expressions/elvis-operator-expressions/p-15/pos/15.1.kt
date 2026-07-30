// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 15 -> sentence 15
 *                expressions, function-literals, lambda-literals -> paragraph 15 -> sentence 15
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand labeled return in forEach lambda type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(xs: List<String?>) {
    xs.forEach {
        val n = it?.length ?: return@forEach
        checkSubtype<Int>(n)
    }
}

// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 14 -> sentence 14
 *                expressions, function-literals, lambda-literals -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: bare return in non-inline lambda with Elvis is not allowed
 */

// TESTCASE NUMBER: 1
fun mapNonInline(xs: List<String?>, transform: (String?) -> Int): List<Int> {
    val result = ArrayList<Int>()
    for (x in xs) result.add(transform(x))
    return result
}

fun test(xs: List<String?>): List<Int> = mapNonInline(xs) { it?.length ?: <!RETURN_NOT_ALLOWED!>return<!> emptyList() }

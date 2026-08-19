// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 12 -> sentence 12
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: callable reference and trailing lambda map calls share result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val xs = listOf("a", "ab")
    checkSubtype<List<Int>>(xs.map(String::length))
    checkSubtype<List<Int>>(xs.map { it.length })
}

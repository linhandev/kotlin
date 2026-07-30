// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, jump-expressions, throw-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: Elvis expression inferring Int cannot initialize non-nullable String variable
 */

// TESTCASE NUMBER: 1
fun test(x: String?) {
    val s: String = <!TYPE_MISMATCH!>x?.length ?: throw Exception()<!>
}

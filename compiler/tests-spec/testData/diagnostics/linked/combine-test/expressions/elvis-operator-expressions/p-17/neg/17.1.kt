// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNREACHABLE_CODE -USELESS_ELVIS
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: parenthesized throw as Elvis left-hand operand yields Int which cannot initialize String
 */

// TESTCASE NUMBER: 1
fun test() {
    val s: String = <!TYPE_MISMATCH!>(throw Exception()) ?: 1<!>
}

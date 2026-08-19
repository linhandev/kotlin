// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: as? List<String> from List<*> reports UNCHECKED_CAST
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a: List<*> = listOf("x")
    checkSubtype<List<String>?>(a <!UNCHECKED_CAST!>as? List<String><!>)
}

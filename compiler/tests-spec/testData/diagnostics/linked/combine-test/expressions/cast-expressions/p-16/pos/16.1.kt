// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: as List<String> reports UNCHECKED_CAST
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a: Any = listOf("a")
    checkSubtype<List<String>>(a <!UNCHECKED_CAST!>as List<String><!>)
}

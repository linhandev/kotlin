// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: Sequence map destructuring matches List semantics
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val r = sequenceOf(1 to 2, 2 to 3).map { (a, b) -> a + b }.toList()
    checkSubtype<List<Int>>(r)
}

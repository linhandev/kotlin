// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 2 -> sentence 2
 *                expressions, call-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: String::isEmpty passed to List.filterNot infers as (String) -> Boolean and yields List<String>, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(xs: List<String>) {
    checkSubtype<List<String>>(xs.filterNot(String::isEmpty))
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 3 -> sentence 3
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: bound member function reference s::length infers type () -> Int, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String) {
    val f: () -> Int = s::length
    checkSubtype<() -> Int>(f)
}

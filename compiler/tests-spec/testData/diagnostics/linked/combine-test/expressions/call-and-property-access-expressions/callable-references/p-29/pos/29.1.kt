// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: bound callable reference on parenthesized expression result (s.uppercase())::length infers () -> Int, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String) {
    val f: () -> Int = (s.uppercase())::length
    checkSubtype<() -> Int>(f)
}

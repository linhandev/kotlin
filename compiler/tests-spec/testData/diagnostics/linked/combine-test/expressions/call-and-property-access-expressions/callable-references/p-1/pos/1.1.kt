// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unbound member function reference String::length infers function type (String) -> Int and is assignable to matching variable
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val f: (String) -> Int = String::length
    checkSubtype<(String) -> Int>(f)
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 23 -> sentence 23
 *                expressions, function-literals, lambda-literals -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: callable reference String::length and lambda { it.length } are interchangeable as (String) -> Int arguments, verifying type inference
 * HELPERS: checkType
 */

fun apply(f: (String) -> Int, s: String): Int = f(s)

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Int>(apply(String::length, "a"))
    checkSubtype<Int>(apply({ it.length }, "a"))
}

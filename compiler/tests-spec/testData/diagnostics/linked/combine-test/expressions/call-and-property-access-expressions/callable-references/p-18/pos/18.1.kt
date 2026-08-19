// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 18 -> sentence 18
 *                overload-resolution, resolving-callable-references -> paragraph 18 -> sentence 18
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: callable reference ::f passed to overloaded higher-order function consumeInt resolved via parameter type (Int) -> Int, verifying type inference
 * HELPERS: checkType
 */

fun consumeInt(f: (Int) -> Int): Int = f(1)
fun consumeAny(f: (Any) -> Any): Any = f(1)

fun f(x: Int): Int = x

// TESTCASE NUMBER: 1
fun case1() {
    val result: Int = consumeInt(::f)
    checkSubtype<Int>(result)
}

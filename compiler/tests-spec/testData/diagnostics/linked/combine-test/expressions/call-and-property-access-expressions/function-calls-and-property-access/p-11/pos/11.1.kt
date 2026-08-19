// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 11 -> sentence 11
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: function-type property as named argument type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Ops {
    val inc: (Int) -> Int = { it + 1 }
}

fun apply(op: (Int) -> Int, x: Int): Int = op(x)

fun case1(o: Ops) {
    checkSubtype<Int>(apply(o.inc, 1))
}

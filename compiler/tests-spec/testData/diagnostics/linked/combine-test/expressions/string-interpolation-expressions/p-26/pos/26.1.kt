// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 26 -> sentence 26
 *                operator-overloading, overview -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: invoke operator call inside ${} interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Fn(val v: Int) {
    operator fun invoke(): Int = v
}

fun case1() {
    val f = Fn(3)
    checkSubtype<String>("r=${f()}")
}

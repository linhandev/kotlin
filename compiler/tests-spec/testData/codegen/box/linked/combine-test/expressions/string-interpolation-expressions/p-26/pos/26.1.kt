// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 26 -> sentence 26
 *                operator-overloading, overview -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: invoke operator call can be interpolated inside ${}
 */

// TESTCASE NUMBER: 1
class Fn(val v: Int) {
    operator fun invoke(): Int = v
}

fun test(f: Fn): String = "r=${f()}"

fun box(): String {
    if (test(Fn(3)) != "r=3") return "NOK"
    if (test(Fn(0)) != "r=0") return "NOK"
    return "OK"
}

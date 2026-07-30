// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 8 -> sentence 8
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: scope functions let/run/also with trailing lambda on nullable receiver
 */

// TESTCASE NUMBER: 1
fun testLet(x: String?): Int = x?.let { it.length } ?: 0

fun testRun(x: String?): Int = x?.run { length } ?: 0

fun testAlso(x: String?): Int {
    var n = -1
    x?.also { n = it.length }
    return if (n < 0) 0 else n
}

fun box(): String {
    if (testLet("abc") != 3) return "NOK: let"
    if (testLet(null) != 0) return "NOK: let-null"
    if (testRun("ab") != 2) return "NOK: run"
    if (testRun(null) != 0) return "NOK: run-null"
    if (testAlso("a") != 1) return "NOK: also"
    if (testAlso(null) != 0) return "NOK: also-null"
    return "OK"
}

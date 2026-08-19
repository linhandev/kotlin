// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: property on implicit this receiver can be interpolated with simple identifier form
 */

// TESTCASE NUMBER: 1
class C(private val v: Int) {
    fun test(): String = "v=$v"
}

fun box(): String {
    if (C(7).test() != "v=7") return "NOK"
    if (C(-1).test() != "v=-1") return "NOK"
    return "OK"
}

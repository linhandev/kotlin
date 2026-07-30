// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: member call on implicit this receiver can be interpolated inside ${}
 */

// TESTCASE NUMBER: 1
class C {
    fun label(): String = "ok"
    fun test(): String = "x=${label()}"
}

fun box(): String {
    if (C().test() != "x=ok") return "NOK"
    return "OK"
}

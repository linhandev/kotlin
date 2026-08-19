// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: custom toString participates in simple identifier interpolation
 */

// TESTCASE NUMBER: 1
data class Id(val v: Int) {
    override fun toString(): String = "#$v"
}

fun test(id: Id): String = "id=$id"

fun box(): String {
    if (test(Id(1)) != "id=#1") return "NOK"
    if (test(Id(42)) != "id=#42") return "NOK"
    return "OK"
}

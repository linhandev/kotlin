// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: $id only captures simple path; qualified path needs ${} form
 */

// TESTCASE NUMBER: 1

class Holder(val value: Int)

fun box(): String {
    val h = Holder(7)
    val withDollarId = "v=$h.value"
    val withExpression = "v=${h.value}"
    return if (withExpression == "v=7" && withDollarId.endsWith(".value") && withDollarId != withExpression) "OK" else "NOK"
}

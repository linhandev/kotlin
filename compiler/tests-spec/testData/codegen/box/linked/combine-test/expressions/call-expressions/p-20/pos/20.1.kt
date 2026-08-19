/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 20 -> sentence 20
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 20 -> sentence 20
 *                expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: named arguments can be combined with trailing lambda
 */

// TESTCASE NUMBER: 1
fun build(tag: String = "d", block: (String) -> String): String = block(tag)

fun box(): String {
    if (build(tag = "x") { it } != "x") return "NOK"
    return "OK"
}

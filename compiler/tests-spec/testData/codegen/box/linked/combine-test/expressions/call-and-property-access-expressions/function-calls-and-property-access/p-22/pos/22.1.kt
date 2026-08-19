// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 22 -> sentence 22
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: trailing lambda call used as expression statement returns Unit
 */

// TESTCASE NUMBER: 1
var consumed = false

fun consume(block: () -> Unit): Unit = block()

fun test(): Unit = consume { consumed = true }

fun box(): String {
    consumed = false
    test()
    if (!consumed) return "NOK"
    return "OK"
}

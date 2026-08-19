/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: fun interface SAM lambda is interchangeable with object literal implementation
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

fun viaLambda(): Int = Op { 2 }.eval()

fun viaObjectLiteral(): Int = object : Op {
    override fun eval(): Int = 2
}.eval()

fun box(): String {
    if (viaLambda() != 2) return "NOK: lambda"
    if (viaObjectLiteral() != 2) return "NOK: object-literal"
    if (viaLambda() != viaObjectLiteral()) return "NOK: mismatch"
    return "OK"
}

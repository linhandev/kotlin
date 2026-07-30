// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: fun interface supports equivalent lambda and object declaration implementations
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

object NamedOp : Op {
    override fun eval(): Int = 1
}

fun viaLambda(): Int = Op { 2 }.eval()
fun viaObject(): Int = NamedOp.eval()

fun box(): String {
    if (viaLambda() != 2) return "NOK: lambda"
    if (viaObject() != 1) return "NOK: object"
    return "OK"
}

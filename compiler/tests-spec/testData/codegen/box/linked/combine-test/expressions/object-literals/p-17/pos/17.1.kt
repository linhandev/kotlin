
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: fun interface can be implemented with object literal
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

fun test(): Int = object : Op {
    override fun eval(): Int = 1
}.eval()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

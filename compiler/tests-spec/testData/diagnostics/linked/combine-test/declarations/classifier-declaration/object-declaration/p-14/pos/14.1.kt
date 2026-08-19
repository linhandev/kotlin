// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: fun interface supports lambda and object declaration implementations with the same interface type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

object NamedOp : Op {
    override fun eval(): Int = 1
}

fun case_1() {
    val lambda: Op = Op { 2 }
    checkSubtype<Op>(lambda)
    checkSubtype<Op>(NamedOp)
    checkSubtype<Int>(lambda.eval())
    checkSubtype<Int>(NamedOp.eval())
}

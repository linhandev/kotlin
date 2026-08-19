// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: fun interface SAM lambda and object literal both yield Op/Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

fun case_1() {
    val byLambda: Op = Op { 2 }
    val byLiteral: Op = object : Op {
        override fun eval(): Int = 2
    }
    checkSubtype<Op>(byLambda)
    checkSubtype<Op>(byLiteral)
    checkSubtype<Int>(byLambda.eval())
    checkSubtype<Int>(byLiteral.eval())
}

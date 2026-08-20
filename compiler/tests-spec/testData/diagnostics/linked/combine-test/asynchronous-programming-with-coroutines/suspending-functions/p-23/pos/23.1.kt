// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 23 -> sentence 23
 *                inheritance, inheriting -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: suspending open member can be overridden by a suspending override type inference
 * HELPERS: checkType
 */

open class B56123 {
    open suspend fun f56123(): Int = 1
}

class D56123 : B56123() {
    override suspend fun f56123(): Int = 2
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<B56123>(D56123())
}

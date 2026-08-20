// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 24 -> sentence 24
 *                inheritance, inheriting -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: abstract suspend fun is implemented by a suspending override in a subclass type inference
 * HELPERS: checkType
 */

abstract class A56124 {
    abstract suspend fun f56124(): Int
}

class Impl56124 : A56124() {
    override suspend fun f56124(): Int = 1
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<A56124>(Impl56124())
}

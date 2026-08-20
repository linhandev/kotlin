// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 21 -> sentence 21
 *                inheritance, inheriting -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: interface suspend member must be implemented with suspend override type inference
 * HELPERS: checkType
 */

interface I56121 {
    suspend fun f56121(): Int
}

class C56121 : I56121 {
    override suspend fun f56121(): Int = 1
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<I56121>(C56121())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 403 -> sentence 403
 * declarations, declaration-visibility -> paragraph 403 -> sentence 403
 * declarations, function-declaration -> paragraph 403 -> sentence 403
 * declarations, function-declaration -> paragraph 403 -> sentence 403
 * NUMBER: 1
 * DESCRIPTION: 局部函数与 private 成员函数作用域不同 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    private fun member(): Int = 2
    fun outer(): Int {
        fun local(): Int = 1
        return local()
    }
}

// TESTCASE NUMBER: 1
fun test(): Int = C().outer()

fun case1() {
    checkSubtype<Int>(test())
}

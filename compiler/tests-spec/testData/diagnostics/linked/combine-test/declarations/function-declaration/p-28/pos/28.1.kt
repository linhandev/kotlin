// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NOT_YET_SUPPORTED_IN_INLINE -NON_LOCAL_RETURN_NOT_ALLOWED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: inline 外层函数体内的局部函数仍可捕获并调用 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun outer(block: () -> Int): Int {
    fun wrap(): Int = block()
    return wrap()
}

// TESTCASE NUMBER: 1
fun test(): Int = outer { 7 }

fun case1() {
    checkSubtype<Int>(test())
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 24 -> sentence 24
 *                type-system, type-kinds, type-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: 泛型局部函数声明保持类型参数
 */

// TESTCASE NUMBER: 1
fun outer(): String {
    fun <T> id(x: T): T = x
    return id("ok")
}

// TESTCASE NUMBER: 1
fun test(): String = outer()

fun box(): String {
    if (test() != "ok") return "NOK"
    return "OK"
}

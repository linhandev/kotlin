// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 1 -> sentence 1
 *                expressions, call-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: 顶层函数声明默认参数后调用可省略
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 2): Int = a + b

// TESTCASE NUMBER: 1
fun test(): Int = f(1)

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: 顶层函数默认实参可引用前置形参
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = a * 2): Int = a + b

// TESTCASE NUMBER: 1
fun test(): Int = f(3)

fun box(): String {
    if (test() != 9) return "NOK"
    return "OK"
}

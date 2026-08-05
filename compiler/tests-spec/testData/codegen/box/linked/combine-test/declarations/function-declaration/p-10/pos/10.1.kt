// WITH_STDLIB
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 10 -> sentence 10
 *                expressions, call-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: 调用局部函数时可使用命名实参跳过中间默认参数
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun g(a: Int, b: Int = 1, c: Int = 2): Int = a + b + c
    return g(10, c = 3)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun box(): String {
    if (test() != 14) return "NOK"
    return "OK"
}

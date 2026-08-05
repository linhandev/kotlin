// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 25 -> sentence 25
 *                expressions, call-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: 局部函数 vararg 与默认尾参组合调用
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun pack(vararg xs: Int, tail: Int = 10): Int = xs.sum() + tail
    return pack(1, 2)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun box(): String {
    if (test() != 13) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 26 -> sentence 26
 *                expressions, call-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: default arguments are re-evaluated on each call when omitted
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    var n = 0
    fun def(): Int = ++n
    fun f(x: Int = def()): Int = x
    return f() + f()
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}

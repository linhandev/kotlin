// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 27 -> sentence 27
 *                declarations, property-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: local function can be declared and called inside property getter
 */

// TESTCASE NUMBER: 1
val total: Int get() {
    fun sum(a: Int, b: Int): Int = a + b
    return sum(2, 3)
}

// TESTCASE NUMBER: 1
fun test(): Int = total

fun box(): String {
    if (test() != 5) return "NOK"
    return "OK"
}

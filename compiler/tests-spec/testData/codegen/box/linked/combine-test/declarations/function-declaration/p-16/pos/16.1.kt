// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: inner local function can capture middle and outer variables
 */

// TESTCASE NUMBER: 1
fun outer(a: Int): Int {
    fun mid(b: Int): Int {
        fun inner(c: Int): Int = a + b + c
        return inner(1)
    }
    return mid(2)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(10)

fun box(): String {
    if (test() != 13) return "NOK"
    return "OK"
}

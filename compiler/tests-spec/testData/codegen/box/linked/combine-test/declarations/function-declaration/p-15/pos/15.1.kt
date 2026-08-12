// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: nested local function declared inside another local function
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun mid(): Int {
        fun inner(): Int = 3
        return inner()
    }
    return mid()
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: local function name can shadow outer function; call resolves to nearest declaration
 */

// TESTCASE NUMBER: 1
fun f(): Int = 0

fun outer(): Int {
    fun f(): Int = 1
    return f()
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun box(): String {
    if (test() != 1) return "NOK"
    if (f() != 0) return "NOK"
    return "OK"
}

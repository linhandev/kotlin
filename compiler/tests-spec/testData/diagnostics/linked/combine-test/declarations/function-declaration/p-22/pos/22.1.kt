// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NOT_YET_SUPPORTED_IN_INLINE -NOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: inline local function can be inlined when called in same body type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    inline fun work(block: () -> Int): Int = block()
    return work { 2 }
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun case1() {
    checkSubtype<Int>(test())
}

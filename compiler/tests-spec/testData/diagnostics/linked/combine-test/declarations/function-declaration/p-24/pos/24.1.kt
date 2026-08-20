// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 24 -> sentence 24
 *                type-system, type-kinds, type-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: generic local function declaration preserves type parameters type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(): String {
    fun <T> id(x: T): T = x
    return id("ok")
}

// TESTCASE NUMBER: 1
fun test(): String = outer()

fun case1() {
    checkSubtype<String>(test())
}

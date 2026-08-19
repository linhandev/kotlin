// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 50 -> sentence 50
 *                runtime-type-information, runtime-available-types -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: when expression is check against List star projection type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = listOf("a", "b")
    checkSubtype<Int>(when (x) {
        is List<*> -> x.size
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = 123
    checkSubtype<Int>(when (x) {
        is List<*> -> x.size
        else -> -1
    })
}

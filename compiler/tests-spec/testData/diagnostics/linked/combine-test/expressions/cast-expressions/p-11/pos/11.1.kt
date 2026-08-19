// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 11 -> sentence 11
 *                type-inference, smart-casts -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: is-branch Int and as String both type-check
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hi"
    checkSubtype<Int>(if (x is String) x.length else -1)
    checkSubtype<String>(x as String)
}

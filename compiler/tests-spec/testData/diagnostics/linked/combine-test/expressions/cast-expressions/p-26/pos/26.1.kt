// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 26 -> sentence 26
 *                type-inference, smart-casts -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: elvis non-null then as String infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Any?) {
    val s = x ?: return
    checkSubtype<Int>((s as String).length)
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 *                expressions, cast-expressions -> paragraph 14 -> sentence 14
 *                type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, elvis-operator-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: is CharSequence then as? String with Elvis infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hello"
    checkSubtype<Int>(if (x is CharSequence) (x as? String)?.length ?: -1 else -1)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = StringBuilder("ab")
    checkSubtype<Int>(if (x is CharSequence) (x as? String)?.length ?: -1 else -1)
}

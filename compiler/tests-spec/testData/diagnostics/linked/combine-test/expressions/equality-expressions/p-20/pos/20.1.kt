// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, when-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: when null branch infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String?) {
    checkSubtype<String>(when (x) {
        null -> "nil"
        else -> x
    })
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 19 -> sentence 19
 *                expressions, when-expressions -> paragraph 19 -> sentence 19
 *                type-inference, smart-casts -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: when is branch smart cast infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hi"
    checkSubtype<String>(when (x) {
        is String -> x.uppercase()
        else -> ""
    })
}

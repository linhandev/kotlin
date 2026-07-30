// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 48 -> sentence 48
 *                expressions, when-expressions -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: when used as statement with is branch only for Any subject compiles without exhaustiveness error
 */

// TESTCASE NUMBER: 1
fun test(x: Any) {
    when (x) {
        is String -> println(x.length)
    }
}

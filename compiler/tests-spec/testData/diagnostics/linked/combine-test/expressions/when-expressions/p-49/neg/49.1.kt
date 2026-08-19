// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 49 -> sentence 49
 *                runtime-type-information, runtime-available-types -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: when expression is check against List with concrete type argument is not runtime-available
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = when (x) {
    is <!CANNOT_CHECK_FOR_ERASED!>List<String><!> -> x.size
    else -> -1
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 46 -> sentence 46
 *                type-inference, smart-casts -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: smart cast from when is branch does not apply outside when expression
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int {
    when (x) {
        is String -> return x.length
        else -> return -1
    }
    return x.<!UNRESOLVED_REFERENCE!>length<!>
}

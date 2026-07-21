// FIR_IDENTICAL
// LANGUAGE: +FunctionalInterfaceConversion
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: fun interface with single abstract function
 */

// TESTCASE NUMBER: 1
fun interface FI {
    fun bar(s: Int): Int
}

fun doIt(fi: FI) {}

fun useSam() {
    doIt { it + 42 }
}

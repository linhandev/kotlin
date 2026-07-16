// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: data class must have at least one data property in primary constructor
 */

// TESTCASE NUMBER: 1
data class <!DATA_CLASS_WITHOUT_PARAMETERS!>A<!>

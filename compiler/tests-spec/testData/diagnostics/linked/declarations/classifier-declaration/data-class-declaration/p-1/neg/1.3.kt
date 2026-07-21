// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: data class primary constructor allows property parameters only and disallows vararg data properties
 */

// TESTCASE NUMBER: 1
data class RegularParam(<!DATA_CLASS_NOT_PROPERTY_PARAMETER!>x: Int<!>)

// TESTCASE NUMBER: 2
data class VarargData(val x: Int, <!DATA_CLASS_VARARG_PARAMETER!>vararg val y: String<!>)

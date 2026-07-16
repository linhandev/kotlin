// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: value class cannot wrap Unit as underlying property
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UnitBox(val u: <!VALUE_CLASS_HAS_INAPPLICABLE_PARAMETER_TYPE!>Unit<!>)

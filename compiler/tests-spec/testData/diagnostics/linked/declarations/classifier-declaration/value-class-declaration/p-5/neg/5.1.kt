// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: value class with non-runtime-available underlying types
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UnitWrapper(val u: <!VALUE_CLASS_HAS_INAPPLICABLE_PARAMETER_TYPE!>Unit<!>)

// TESTCASE NUMBER: 2
@JvmInline
value class NothingWrapper(val n: <!VALUE_CLASS_HAS_INAPPLICABLE_PARAMETER_TYPE!>Nothing<!>)

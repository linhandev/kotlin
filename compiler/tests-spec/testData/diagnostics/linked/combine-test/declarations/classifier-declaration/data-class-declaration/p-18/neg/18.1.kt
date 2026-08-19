// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 18 -> sentence 18
 *                inheritance, inheriting -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: data class cannot inherit from another data class
 */

// TESTCASE NUMBER: 1
data class Base(val x: Int)
<!DATA_CLASS_OVERRIDE_CONFLICT, DATA_CLASS_OVERRIDE_DEFAULT_VALUES_ERROR!>data<!> <!DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES!>class Child<!>(val y: Int) : <!FINAL_SUPERTYPE!>Base<!>(1)

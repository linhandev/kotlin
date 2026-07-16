// FIR_IDENTICAL
// DIAGNOSTICS: -INLINE_CLASS_DEPRECATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: inline and value modifiers cannot be used together
 */

// TESTCASE NUMBER: 1
@JvmInline
<!INCOMPATIBLE_MODIFIERS!>inline<!> <!INCOMPATIBLE_MODIFIERS!>value<!> class A(val x: Int)

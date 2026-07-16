// FIR_IDENTICAL
// DIAGNOSTICS: -INLINE_CLASS_DEPRECATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: value class cannot be combined with data modifier or declared as enum class
 */

// TESTCASE NUMBER: 1
<!INCOMPATIBLE_MODIFIERS, VALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION!>value<!> <!INCOMPATIBLE_MODIFIERS!>data<!> class D1(val x: String)

// TESTCASE NUMBER: 2
@JvmInline
<!WRONG_MODIFIER_TARGET!>value<!> enum class E1 { A }

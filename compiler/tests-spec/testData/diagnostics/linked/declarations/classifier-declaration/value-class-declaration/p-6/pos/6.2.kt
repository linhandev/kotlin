// LANGUAGE: +InlineClasses
// DIAGNOSTICS: -INLINE_CLASS_DEPRECATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: structural equality works on value classes
 */

// TESTCASE NUMBER: 1
inline class Amount(val cents: Int)

fun compare(a: Amount, b: Amount): Boolean = a == b

// FIR_IDENTICAL
// DIAGNOSTICS: -INLINE_CLASS_DEPRECATED -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT
// LANGUAGE: +InlineClasses

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: legacy inline class declaration compiles
 */

// TESTCASE NUMBER: 1
inline class Legacy(val x: Int)

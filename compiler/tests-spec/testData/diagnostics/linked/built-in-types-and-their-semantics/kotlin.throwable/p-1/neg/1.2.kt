// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.throwable -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: no subtype of kotlin.Throwable may declare type parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1<!GENERIC_THROWABLE_SUBCLASS!><T><!> : Throwable()


// TESTCASE NUMBER: 2
class Case2<!GENERIC_THROWABLE_SUBCLASS!><T><!> : Exception()


// TESTCASE NUMBER: 3
class Case3 : Throwable() {
    class Nested<!GENERIC_THROWABLE_SUBCLASS!><T><!> : Throwable()
}

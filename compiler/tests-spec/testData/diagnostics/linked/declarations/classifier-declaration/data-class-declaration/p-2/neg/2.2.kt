// FIR_IDENTICAL
// LANGUAGE: +DataObjects
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: data object cannot override equals or hashCode
 */

// TESTCASE NUMBER: 1
data object Override {
    <!DATA_OBJECT_CUSTOM_EQUALS_OR_HASH_CODE!>override<!> fun equals(other: Any?): Boolean = true
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 75 -> sentence 75
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 75 -> sentence 75
 * NUMBER: 1
 * DESCRIPTION: nested generic empty data class rejected (class + data + type params)
 */

// TESTCASE NUMBER: 1
class Holder { data class Empty<T><!DATA_CLASS_WITHOUT_PARAMETERS!>()<!> }

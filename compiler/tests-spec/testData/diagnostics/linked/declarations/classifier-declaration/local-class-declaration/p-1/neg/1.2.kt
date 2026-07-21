// LANGUAGE: +ProhibitLocalAnnotations
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, local-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: local annotation class is not allowed
 */

// TESTCASE NUMBER: 1
fun foo() {
    <!LOCAL_ANNOTATION_CLASS_ERROR!>annotation class LocalAnno<!>
}

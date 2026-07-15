// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-annotation-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: annotations, built-in-annotations, kotlin.annotation.Target -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: built-in Target and Repeatable meta-annotations restrict annotation usage
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.FUNCTION)
annotation class FunOnly

<!WRONG_ANNOTATION_TARGET!>@FunOnly<!>
class Case1

// TESTCASE NUMBER: 2
@Target(AnnotationTarget.CLASS)
annotation class ClassOnly(val value: Int)

@ClassOnly(1) <!REPEATED_ANNOTATION!>@ClassOnly(2)<!>
class Case2

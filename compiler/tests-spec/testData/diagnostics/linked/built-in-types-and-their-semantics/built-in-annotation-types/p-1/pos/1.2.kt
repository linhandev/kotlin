// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEPRECATION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-annotation-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: annotations, built-in-annotations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: built-in kotlin annotation types Target Retention Repeatable Deprecated and Suppress
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class MetaAnn(val tag: String)

@MetaAnn("f")
fun case_1() {}

// TESTCASE NUMBER: 2
@Deprecated("obsolete", ReplaceWith("case_2()"), DeprecationLevel.WARNING)
fun case_1_deprecated() {}

@Suppress("UNUSED_PARAMETER")
fun case_2(unused: Int) {}

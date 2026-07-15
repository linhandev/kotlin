// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-annotation-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation classes cannot declare supertypes functions or mutable properties
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class Case1  : <!SUPERTYPES_FOR_ANNOTATION_CLASS!>Annotation<!>

// TESTCASE NUMBER: 2
annotation class Case2(val x: Int) {
    <!ANNOTATION_CLASS_MEMBER!>fun f() {}<!>
}

// TESTCASE NUMBER: 3
annotation class Case3(<!VAR_ANNOTATION_PARAMETER!>var<!> x: Int)

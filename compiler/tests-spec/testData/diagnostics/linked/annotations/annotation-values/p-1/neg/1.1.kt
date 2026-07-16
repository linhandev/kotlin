// FIR_IDENTICAL
// LANGUAGE: -NestedClassesInAnnotations
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation classes cannot declare supertypes member functions or mutable properties
 */

// TESTCASE NUMBER: 1
annotation class Supertype17111 : <!SUPERTYPES_FOR_ANNOTATION_CLASS!>Annotation<!>

// TESTCASE NUMBER: 2
annotation class MemberFun17111(val x: Int) {
    <!ANNOTATION_CLASS_MEMBER!>fun f() {}<!>
}

// TESTCASE NUMBER: 3
annotation class MutableParam17111(<!VAR_ANNOTATION_PARAMETER!>var<!> x: Int)

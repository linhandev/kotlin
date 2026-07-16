// FIR_IDENTICAL
// LANGUAGE: -NestedClassesInAnnotations
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation class cannot use invalid supertype, declare member function, var parameter, companion object, or nested class
 */

// TESTCASE NUMBER: 1
annotation class Case1 : <!SUPERTYPES_FOR_ANNOTATION_CLASS!>Annotation<!>

// TESTCASE NUMBER: 2
annotation class Case2(val x: Int) {
    <!ANNOTATION_CLASS_MEMBER!>fun f() {}<!>
}

// TESTCASE NUMBER: 3
annotation class Case3(<!VAR_ANNOTATION_PARAMETER!>var<!> x: Int)

// TESTCASE NUMBER: 4
annotation class Case4(val x: Int) {
    companion <!ANNOTATION_CLASS_MEMBER!>object<!>
}

// TESTCASE NUMBER: 5
annotation class Case5(val x: Int) {
    <!ANNOTATION_CLASS_MEMBER!>class Nested()<!>
}

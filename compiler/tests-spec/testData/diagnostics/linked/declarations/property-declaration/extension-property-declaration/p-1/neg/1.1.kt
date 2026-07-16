// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, extension-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: extension property without accessors, with backing field initializer, and mutable without setter are rejected
 */

// TESTCASE NUMBER: 1
<!EXTENSION_PROPERTY_MUST_HAVE_ACCESSORS_OR_BE_ABSTRACT!>var String.bad: Int<!>

// TESTCASE NUMBER: 2
val String.withField = <!EXTENSION_PROPERTY_WITH_BACKING_FIELD!>"x"<!>

// TESTCASE NUMBER: 3
<!EXTENSION_PROPERTY_MUST_HAVE_ACCESSORS_OR_BE_ABSTRACT!>var Int.mutableWithoutSetter: Int<!>

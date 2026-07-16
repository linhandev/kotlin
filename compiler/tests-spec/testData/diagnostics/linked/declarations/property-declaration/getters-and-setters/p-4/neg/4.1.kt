// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: accessors cannot use field when property has no backing field
 */

// TESTCASE NUMBER: 1
val String.withoutBacking: Int
    get() = <!UNRESOLVED_REFERENCE!>field<!>

// TESTCASE NUMBER: 2
var String.customOnly: Int
    get() = 1
    set(arg) { <!UNRESOLVED_REFERENCE!>field<!> = arg }

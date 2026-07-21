// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, type-alias -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: mutually recursive and self-referential type alias expansion is forbidden
 */

// TESTCASE NUMBER: 1
typealias AliasA = <!RECURSIVE_TYPEALIAS_EXPANSION!>AliasB<!>

// TESTCASE NUMBER: 2
typealias AliasB = <!RECURSIVE_TYPEALIAS_EXPANSION!>AliasA<!>

// TESTCASE NUMBER: 3
typealias SelfRef = <!RECURSIVE_TYPEALIAS_EXPANSION!>List<SelfRef><!>

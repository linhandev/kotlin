// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, inheritance-from-built-in-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: InheritInt513 : Int and InheritBoolean513 : Boolean report FINAL_SUPERTYPE
 */

// TESTCASE NUMBER: 1
class InheritInt513 : <!FINAL_SUPERTYPE, INVISIBLE_MEMBER!>Int<!>()

// TESTCASE NUMBER: 2
class InheritBoolean513 : <!FINAL_SUPERTYPE, INVISIBLE_MEMBER!>Boolean<!>()

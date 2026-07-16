// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: accessor parameter and return types must match property type
 */

// TESTCASE NUMBER: 1
val count: Int
    get(): <!WRONG_GETTER_RETURN_TYPE!>String<!> = "wrong"

// TESTCASE NUMBER: 2
var label: Int = 0
    set(value: <!WRONG_SETTER_PARAMETER_TYPE!>String<!>) { field = <!ASSIGNMENT_TYPE_MISMATCH!>value<!> }

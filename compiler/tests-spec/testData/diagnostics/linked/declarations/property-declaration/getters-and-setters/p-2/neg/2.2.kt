// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: setter parameter type mismatch with property type
 */

// TESTCASE NUMBER: 1
class Holder {
    var count: Int = 0
        set(value: <!WRONG_SETTER_PARAMETER_TYPE!>Double<!>) {
            field = <!TYPE_MISMATCH!>value<!>
        }
}

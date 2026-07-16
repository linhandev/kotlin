// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, mutable-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: custom setter parameter type must match property type
 */

// TESTCASE NUMBER: 1
class Holder {
    var x: Int = 0
        set(value: <!WRONG_SETTER_PARAMETER_TYPE!>String<!>) {
            field = <!TYPE_MISMATCH!>value<!>
        }
}

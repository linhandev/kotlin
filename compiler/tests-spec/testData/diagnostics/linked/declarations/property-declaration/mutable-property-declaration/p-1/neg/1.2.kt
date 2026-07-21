// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, mutable-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: custom setter without parameter name is forbidden
 */

// TESTCASE NUMBER: 1
class Holder {
    var count: Int = 0
        set(<!SYNTAX!><!>: Int) {
            field = 0
        }
}

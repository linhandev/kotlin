// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: setter parameter shadows outer name in accessor scope
 */

// TESTCASE NUMBER: 1
class Holder {
    var item: String = "a"
        set(value) {
            field = value
        }
}

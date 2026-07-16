// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: accessor bodies introduce parameter scopes linked to enclosing property scope
 */

// TESTCASE NUMBER: 1
private val limit = 10

var capped: Int = 0
    set(newValue) {
        field = if (newValue > limit) limit else newValue
    }

// TESTCASE NUMBER: 2
class Holder(private val offset: Int) {
    var total: Int = 0
        get() = field + offset
        set(value) {
            field = value - offset
        }
}

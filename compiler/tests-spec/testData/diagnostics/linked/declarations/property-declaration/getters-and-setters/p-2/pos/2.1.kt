// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: getter and setter with explicit return and parameter types matching property type compile successfully
 */

// TESTCASE NUMBER: 1
val readOnly: Int
    get(): Int = 1

// TESTCASE NUMBER: 2
var mutable: Int = 0
    set(value: Int) { field = value }

// TESTCASE NUMBER: 3
class Holder {
    var tagged: String = "ok"
        get(): String = field
        set(newValue: String) { field = newValue }
}

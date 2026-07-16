// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: getter-only val, custom setter assigning field, and class property with both accessors compile successfully
 */

// TESTCASE NUMBER: 1
val x: Int
    get() = 1

// TESTCASE NUMBER: 2
var y: Int = 0
    set(v) { field = v }

// TESTCASE NUMBER: 3
class AccessorHolder {
    var z: Int = 0
        get() = field + 1
        set(value) { field = value }
}

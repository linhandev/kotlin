// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: val with initializer, lazy delegate, custom accessor, and extension property getter compile successfully
 */

// TESTCASE NUMBER: 1
val x: Int = 1

// TESTCASE NUMBER: 2
val y by lazy { 2 }

// TESTCASE NUMBER: 3
class Holder {
    var z: Int = 0
        get() = field
        set(value) { field = value }
}

// TESTCASE NUMBER: 4
val String.lengthPlusOne: Int
    get() = length + 1

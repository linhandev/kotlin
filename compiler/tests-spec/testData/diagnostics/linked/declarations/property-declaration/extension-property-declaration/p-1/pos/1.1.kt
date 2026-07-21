// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, extension-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: extension properties require explicit accessors and use receiver parameter
 */

// TESTCASE NUMBER: 1
val String.lastChar: Char
    get() = this[lastIndex]

// TESTCASE NUMBER: 2
val Int.foo: Int
    get() = this + 1

var StringBuilder.tracked: String
    get() = toString()
    set(value) {
        clear()
        append(value)
    }

// TESTCASE NUMBER: 3
class Bar {
    val Int.nested: Int
        get() = this * 2
}

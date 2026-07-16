// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: custom getter intercepts property read
 */

// TESTCASE NUMBER: 1
class Tracked(private var storage: Int) {
    var value: Int
        get() = storage
        set(v) { storage = v }
}

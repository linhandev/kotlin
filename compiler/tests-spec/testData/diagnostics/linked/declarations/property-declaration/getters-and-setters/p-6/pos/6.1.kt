// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: inline properties use custom accessors without backing fields
 */

// TESTCASE NUMBER: 1
inline val computed: Int
    get() = 42

// TESTCASE NUMBER: 2
class InlineHolder {
    inline var doubled: Int
        get() = 2
        set(_) {}
}

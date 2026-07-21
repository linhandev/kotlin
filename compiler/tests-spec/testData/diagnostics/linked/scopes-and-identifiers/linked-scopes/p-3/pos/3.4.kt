// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: Holder634.copyDefault() calls companion default()
 */

// TESTCASE NUMBER: 1
class Holder634(val value: Int) {
    companion object {
        fun default(): Holder634 = Holder634(0)
    }

    fun copyDefault(): Holder634 = default()
}

fun case1(h: Holder634): Holder634 = h.copyDefault()

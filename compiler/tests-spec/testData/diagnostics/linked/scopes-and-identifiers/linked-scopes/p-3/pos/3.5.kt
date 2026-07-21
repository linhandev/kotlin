// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 5
 * DESCRIPTION: init block reads constructor parameter name into length
 */

// TESTCASE NUMBER: 1
class User635(val name: String) {
    val length: Int

    init {
        length = name.length
    }
}

fun case1(u: User635): Int = u.length

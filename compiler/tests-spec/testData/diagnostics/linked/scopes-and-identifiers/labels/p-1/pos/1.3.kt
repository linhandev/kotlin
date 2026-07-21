// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, labels -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: return@label from labeled lambda in inline runLabeled
 */

// TESTCASE NUMBER: 1
inline fun runLabeled(block: () -> Unit) {
    block()
}

fun case1(): String {
    runLabeled label@{
        return@label
    }
    return "done"
}

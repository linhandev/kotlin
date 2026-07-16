// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, late-initialized-properties -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: lateinit top-level property assignment before use
 */

// TESTCASE NUMBER: 1
lateinit var globalName: String

fun initAndRead(): String {
    globalName = "ready"
    return globalName
}

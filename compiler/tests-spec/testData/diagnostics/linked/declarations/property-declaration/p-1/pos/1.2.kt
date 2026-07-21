// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: property with custom getter and delegated initialization
 */

// TESTCASE NUMBER: 1
val computed: Int
    get() = 42

// TESTCASE NUMBER: 2
val delegated by lazy { "ok" }

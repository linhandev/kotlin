// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: lazy delegated property initializes on first access
 */

// TESTCASE NUMBER: 1
val lazyValue: String by lazy { "initialized" }

fun read(): String = lazyValue

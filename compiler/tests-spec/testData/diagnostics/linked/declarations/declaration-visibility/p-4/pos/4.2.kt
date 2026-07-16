// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: internal declarations are accessible from other files in the same module
 */

// FILE: internalDef.kt
internal fun sharedInternal(): Int = 42

internal class InternalHolder(val value: Int)

// FILE: internalUse.kt
// TESTCASE NUMBER: 1
internal fun useInternalFromOtherFile(): Int = sharedInternal()

// TESTCASE NUMBER: 2
internal fun readInternalFromOtherFile(): Int = InternalHolder(5).value

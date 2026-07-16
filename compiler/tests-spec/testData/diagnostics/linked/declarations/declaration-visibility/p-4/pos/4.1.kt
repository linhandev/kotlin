// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: internal declarations are accessible within the same module
 */

// TESTCASE NUMBER: 1
internal fun moduleInternal(): Int = 1

fun useInternalInModule(): Int = moduleInternal()

// TESTCASE NUMBER: 2
class InternalHolder {
    internal val value = 2
}

fun readInternalField(holder: InternalHolder): Int = holder.value

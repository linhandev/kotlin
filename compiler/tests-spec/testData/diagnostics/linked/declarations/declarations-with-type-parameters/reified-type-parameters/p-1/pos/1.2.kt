// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: inline property accessors may declare reified type parameters
 */

// TESTCASE NUMBER: 1
inline val <reified T> T.runtimeName: String
    get() = T::class.simpleName ?: ""

fun checkRuntimeName(): Boolean = 1.runtimeName == "Int"

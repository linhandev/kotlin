// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: inline reified type parameter supports is-check and class name lookup in inline function body
 */

// TESTCASE NUMBER: 1
inline fun <reified T> isA(value: Any?): Boolean = value is T

fun checkIsA(): Boolean = isA<String>("x") && !isA<Int>("x")

// TESTCASE NUMBER: 2
inline fun <reified T> className(): String = T::class.simpleName ?: ""

fun checkClassName(): Boolean = className<Int>() == "Int"

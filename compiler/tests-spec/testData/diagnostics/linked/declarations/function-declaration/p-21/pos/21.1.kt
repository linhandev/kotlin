// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: inline functions may declare reified type parameters for runtime type checks and class literals
 */

// TESTCASE NUMBER: 1
inline fun <reified T> isInstance(value: Any?): Boolean = value is T

fun checkIsInstance(): Boolean = isInstance<String>("ok") && !isInstance<Int>("ok")

// TESTCASE NUMBER: 2
inline fun <reified T> classLiteral(): String = T::class.simpleName ?: ""

fun checkClassLiteral(): Boolean = classLiteral<String>() == "String"

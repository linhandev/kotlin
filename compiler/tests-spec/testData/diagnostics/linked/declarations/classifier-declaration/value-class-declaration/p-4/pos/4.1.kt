// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: value class implicit equals hashCode based on underlying property
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Password(val s: String)

fun testEquals(a: Password, b: Password): Boolean = a == b

fun testHashCode(a: Password): Int = a.hashCode()

fun testToString(a: Password): String = a.toString()

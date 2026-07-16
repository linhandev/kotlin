// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: class with type parameter, primary constructor, inheritance, and interface implementation compiles successfully
 */

// TESTCASE NUMBER: 1
open class Base

interface I {
    fun value(): Int
}

class Foo<T>(val x: T) : Base(), I {
    override fun value(): Int = 0
}

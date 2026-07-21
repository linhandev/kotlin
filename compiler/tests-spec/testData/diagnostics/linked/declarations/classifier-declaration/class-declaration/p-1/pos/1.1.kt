// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class declaration with single class and multiple interface supertypes
 */

// TESTCASE NUMBER: 1
open class Base

interface I1 {
    fun v1(): Int
}

interface I2 {
    fun v2(): Int
}

class C : Base(), I1, I2 {
    override fun v1(): Int = 1
    override fun v2(): Int = 2
}

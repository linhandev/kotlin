// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: InternalDerived531 uses inherited internal helper() and tag
 */

// TESTCASE NUMBER: 1
open class InternalBase531 {
    internal open fun helper(): Int = 1
    internal open val tag: String get() = "ok"
}

class InternalDerived531 : InternalBase531() {
    fun useInherited(): Int {
        tag
        return helper()
    }
}

fun case1(d: InternalDerived531): Int = d.useInherited()

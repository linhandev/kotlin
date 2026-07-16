// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, matching-and-subsumption-of-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Dog520 speak() returns String over Any; IntBox520 content returns Int over Any
 */

open class Animal520 {
    open fun speak(): Any = "sound"
}

class Dog520 : Animal520() {
    override fun speak(): String = "woof"
}

open class Box520 {
    open val content: Any get() = 1
}

class IntBox520 : Box520() {
    override val content: Int get() = 42
}

// TESTCASE NUMBER: 1
fun case1(d: Dog520, b: IntBox520): Int {
    d.speak()
    return b.content
}

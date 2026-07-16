// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: protected member function and protected property are accessible in direct subclass
 */

// TESTCASE NUMBER: 1
open class Base {
    protected open fun guarded(): Int = 1
}

class Derived : Base() {
    fun readProtected(): Int = guarded()
}

// TESTCASE NUMBER: 2
open class BaseWithField {
    protected val label = "ok"
}

class Child : BaseWithField() {
    fun copyLabel(): String = label
}

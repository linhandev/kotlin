// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, super-forms -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: inner class super<Klazz> without @type when supertype belongs to outer reports NOT_A_SUPERTYPE
 */

interface A {
    fun foo() {}
}

interface B {
    fun foo() {}
}

open class C : A {
    override fun foo() {}
}

class D : C(), B {
    inner class Inner {
// TESTCASE NUMBER: 1
        fun case1() {
            super<<!NOT_A_SUPERTYPE!>C<!>>.foo()
        }
    }

    override fun foo() {}
}

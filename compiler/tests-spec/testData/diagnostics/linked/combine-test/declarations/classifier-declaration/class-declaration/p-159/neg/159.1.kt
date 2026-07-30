// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 159 -> sentence 159
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 159 -> sentence 159
 *                declarations, declaration-visibility -> paragraph 159 -> sentence 159
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 159 -> sentence 159
 * NUMBER: 1
 * DESCRIPTION: private superclass members remain invisible in subclasses even when constructor delegation and inheritance are otherwise valid in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(seed: Int) {
    private val secret: Int = seed
}

class Child(seed: Int) : Base(seed) {
    fun get(): Int = <!INVISIBLE_MEMBER!>secret<!>
}

// TESTCASE NUMBER: 2
open class Vault(val label: String) {
    private fun unlock(): String = label
}

class Client(label: String) : Vault(label) {
    fun open(): String = <!INVISIBLE_MEMBER!>unlock<!>()
}

// TESTCASE NUMBER: 3
open class GrandParent(val id: Int) {
    private val hidden: Int = id * 10
}

open class Parent(id: Int) : GrandParent(id)

class Kid(id: Int) : Parent(id) {
    fun leak(): Int = <!INVISIBLE_MEMBER!>hidden<!>
}

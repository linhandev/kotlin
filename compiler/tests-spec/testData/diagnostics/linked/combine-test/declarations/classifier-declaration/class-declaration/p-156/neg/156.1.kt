// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 156 -> sentence 156
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 156 -> sentence 156
 *                inheritance, overriding -> paragraph 156 -> sentence 156
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 156 -> sentence 156
 * NUMBER: 1
 * DESCRIPTION: non-open superclass member cannot be overridden even when the subclass properly delegates to the superclass constructor in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int) {
    fun f(): Int = x
}

class Child(x: Int) : Base(x) {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun f(): Int = x * 2
}

// TESTCASE NUMBER: 2
open class Holder(val label: String) {
    val tag: String get() = label
}

class BadHolder(label: String) : Holder(label) {
    <!OVERRIDING_FINAL_MEMBER!>override<!> val tag: String get() = "bad $label"
}

// TESTCASE NUMBER: 3
open class Mixed(val seed: Int) {
    open fun allowed(): Int = seed
    fun fixed(): Int = seed + 1
}

class MixedImpl(seed: Int) : Mixed(seed) {
    override fun allowed(): Int = seed * 10
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun fixed(): Int = seed * 100
}

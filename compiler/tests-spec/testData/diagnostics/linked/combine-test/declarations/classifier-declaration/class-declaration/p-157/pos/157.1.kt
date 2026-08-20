// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 157 -> sentence 157
 * PRIMARY LINKS: inheritance, overriding -> paragraph 157 -> sentence 157
 *                inheritance, inheriting -> paragraph 157 -> sentence 157
 *                expressions, super-forms -> paragraph 157 -> sentence 157
 * NUMBER: 1
 * DESCRIPTION: type inference for overriding members that call the immediate superclass implementation via super in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val prefix: String) {
    open fun render(): String = prefix
}

class Child(prefix: String, val suffix: String) : Base(prefix) {
    override fun render(): String = super.render() + suffix
}

fun case1() {
    val c = Child("B", "C")
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.render() checkType { check<String>() }
    val asBase: Base = c
    asBase.render() checkType { check<String>() }
}

// TESTCASE NUMBER: 2
open class A {
    open val tag: String get() = "A"
}

open class B : A() {
    override val tag: String get() = super.tag + "B"
}

class C : B() {
    override val tag: String get() = super.tag + "C"
}

fun case2() {
    val c = C()
    c checkType { check<C>() }
    checkSubtype<B>(c)
    checkSubtype<A>(c)
    c.tag checkType { check<String>() }
    val asA: A = c
    asA.tag checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class Counter(val start: Int) {
    open fun next(n: Int): Int = n + 1
}

class DoubleStep(start: Int) : Counter(start) {
    override fun next(n: Int): Int = super.next(n) + 1
}

fun case3() {
    val d = DoubleStep(0)
    d checkType { check<DoubleStep>() }
    checkSubtype<Counter>(d)
    d.next(1) checkType { check<Int>() }
    val asCounter: Counter = d
    asCounter.next(1) checkType { check<Int>() }
}

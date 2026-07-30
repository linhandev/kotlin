// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 157 -> sentence 157
 * PRIMARY LINKS: inheritance, overriding -> paragraph 157 -> sentence 157
 *                inheritance, inheriting -> paragraph 157 -> sentence 157
 *                expressions, super-forms -> paragraph 157 -> sentence 157
 * NUMBER: 1
 * DESCRIPTION: overriding member invokes the immediate superclass implementation via super, combined with constructor delegation and dynamic dispatch in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val prefix: String) {
    open fun render(): String = prefix
}

class Child(prefix: String, val suffix: String) : Base(prefix) {
    override fun render(): String = super.render() + suffix
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

// TESTCASE NUMBER: 3
open class Counter(val start: Int) {
    open fun next(n: Int): Int = n + 1
}

class DoubleStep(start: Int) : Counter(start) {
    override fun next(n: Int): Int = super.next(n) + 1
}

fun viaBaseRef(): String {
    val overridden: Base = Child("B", "C")
    return overridden.render()
}

fun box(): String {
    if (Child("B", "C").render() != "BC") return "NOK: child-render"
    if (viaBaseRef() != "BC") return "NOK: base-ref-render"
    if (Base("B").render() != "B") return "NOK: base-render"

    if (C().tag != "ABC") return "NOK: c-tag"
    if (B().tag != "AB") return "NOK: b-tag"
    if (A().tag != "A") return "NOK: a-tag"
    if ((C() as A).tag != "ABC") return "NOK: c-as-a-tag"

    if (DoubleStep(0).next(1) != 3) return "NOK: double-step"
    if (Counter(0).next(1) != 2) return "NOK: counter"
    if ((DoubleStep(0) as Counter).next(10) != 12) return "NOK: double-step-as-counter"
    return "OK"
}

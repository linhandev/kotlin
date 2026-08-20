// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 185 -> sentence 185
 * PRIMARY LINKS: inheritance, overriding -> paragraph 185 -> sentence 185
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 185 -> sentence 185
 *                inheritance, inheriting -> paragraph 185 -> sentence 185
 * NUMBER: 1
 * DESCRIPTION: override with a more specific parameter type than the open superclass member is rejected (NOTHING_TO_OVERRIDE) because parameter types are invariant in a class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun take(x: Number) {}
}

class Child : Base() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun take(x: Int) {}
}

// TESTCASE NUMBER: 2
open class Sink {
    open fun accept(value: CharSequence) {}
}

class StrictSink : Sink() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun accept(value: String) {}
}

// TESTCASE NUMBER: 3
open class Box {
    open fun put(item: Any) {}
}

class IntBox : Box() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun put(item: Int) {}
}

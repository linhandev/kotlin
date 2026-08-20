// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 189 -> sentence 189
 * PRIMARY LINKS: inheritance, overriding -> paragraph 189 -> sentence 189
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 189 -> sentence 189
 *                inheritance, inheriting -> paragraph 189 -> sentence 189
 * NUMBER: 1
 * DESCRIPTION: final override seals an open superclass member so a further subclass cannot override it (OVERRIDING_FINAL_MEMBER) in a class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
}

open class Mid : Base() {
    final override fun f(): Int = 2
}

class Leaf : Mid() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun f(): Int = 3
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
}

open class FixedMeter : Meter() {
    final override val reading: Int = 2
}

class ScaledMeter : FixedMeter() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> val reading: Int = 3
}

// TESTCASE NUMBER: 3
open class Root {
    open fun tag(): String = "R"
}

open class Middle : Root() {
    final override fun tag(): String = "M"
}

open class NearLeaf : Middle()

class FarLeaf : NearLeaf() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun tag(): String = "L"
}

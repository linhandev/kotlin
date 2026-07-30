// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 195 -> sentence 195
 * PRIMARY LINKS: inheritance, overriding -> paragraph 195 -> sentence 195
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 195 -> sentence 195
 *                inheritance, inheriting -> paragraph 195 -> sentence 195
 * NUMBER: 1
 * DESCRIPTION: identically named members from multiple interfaces with incompatible return types cannot be satisfied by a single override in a class declaration (RETURN_TYPE_MISMATCH_ON_OVERRIDE / PROPERTY_TYPE_MISMATCH_ON_OVERRIDE)
 */

// TESTCASE NUMBER: 1
interface A {
    fun f(): Int
}

interface B {
    fun f(): String
}

class C : A, B {
    override fun f(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}

// TESTCASE NUMBER: 2
interface Left {
    fun tag(): CharSequence
}

interface Right {
    fun tag(): Int
}

class Clash : Left, Right {
    override fun tag(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>CharSequence<!> = "x"
}

// TESTCASE NUMBER: 3
interface P {
    val code: Int
}

interface Q {
    val code: String
}

class PQ : P, Q {
    override val code: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}

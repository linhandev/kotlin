// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 205 -> sentence 205
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 205 -> sentence 205
 *                inheritance, overriding -> paragraph 205 -> sentence 205
 * NUMBER: 1
 * DESCRIPTION: identically named abstract members from two interfaces with incompatible return types cannot be satisfied by a single override in a class declaration (RETURN_TYPE_MISMATCH_ON_OVERRIDE); distinct from p-195 by covering both override-return choices and Boolean/Double clash
 */

// TESTCASE NUMBER: 1
interface RetA {
    fun f(): Int
}

interface RetB {
    fun f(): String
}

class BadRetInt : RetA, RetB {
    override fun f(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}

// TESTCASE NUMBER: 2
interface RetC {
    fun g(): Int
}

interface RetD {
    fun g(): String
}

class BadRetString : RetC, RetD {
    override fun g(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}

// TESTCASE NUMBER: 3
interface RetE {
    fun h(): Boolean
}

interface RetF {
    fun h(): Double
}

class BadRetBool : RetE, RetF {
    override fun h(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Boolean<!> = true
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 220 -> sentence 220
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 220 -> sentence 220
 *                inheritance, overriding -> paragraph 220 -> sentence 220
 * NUMBER: 1
 * DESCRIPTION: a widened common-supertype override (Any/Number/CharSequence) cannot satisfy two interfaces with mutually incompatible return types (RETURN_TYPE_MISMATCH_ON_OVERRIDE); contrasts with p-195/p-205 choosing one side and with p-219 covariant success when both declare the same general type
 */

// TESTCASE NUMBER: 1
interface LeftInt {
    fun f(): Int
}

interface RightString {
    fun f(): String
}

class BadAny : LeftInt, RightString {
    override fun f(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Any<!> = 1
}

// TESTCASE NUMBER: 2
interface LeftLong {
    fun g(): Long
}

interface RightBool {
    fun g(): Boolean
}

class BadNumber : LeftLong, RightBool {
    override fun g(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Number<!> = 1L
}

// TESTCASE NUMBER: 3
interface LeftCs {
    fun h(): String
}

interface RightInt {
    fun h(): Int
}

class BadCharSequence : LeftCs, RightInt {
    override fun h(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>CharSequence<!> = "x"
}

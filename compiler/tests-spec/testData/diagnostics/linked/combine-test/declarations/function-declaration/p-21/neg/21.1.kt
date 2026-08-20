// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 21 -> sentence 21
 *                inheritance, overriding -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: local function cannot override member function
 */

// TESTCASE NUMBER: 1
open class B { open fun f(): Int = 0 }

fun outer(b: B): Int {
    <!WRONG_MODIFIER_TARGET!>override<!> fun f(): Int = 1
    return b.f()
}

fun test() = outer(B())

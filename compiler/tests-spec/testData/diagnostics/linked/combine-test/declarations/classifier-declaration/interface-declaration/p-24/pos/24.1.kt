// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: type inference for interface-typed parameter receiving override of interface default function body
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface DispatchI {
    fun f(): Int = 1
}

class UseDefault : DispatchI

class OverrideDefault : DispatchI {
    override fun f(): Int = 2
}

fun test(i: DispatchI = OverrideDefault()): Int = i.f()

fun case1() {
    checkSubtype<Int>(test())
    checkSubtype<Int>(test(OverrideDefault()))
    checkSubtype<Int>(test(UseDefault()))
    val i: DispatchI = OverrideDefault()
    checkSubtype<DispatchI>(i)
    checkSubtype<Int>(i.f())
}

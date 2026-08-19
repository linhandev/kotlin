// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 21 -> sentence 21
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: type inference when class delegation inherits interface default function body from delegatee
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface DefaultFn {
    fun f(): Int = 1
}

class Impl : DefaultFn

class OverrideImpl : DefaultFn {
    override fun f(): Int = 2
}

class Delegated(b: DefaultFn) : DefaultFn by b

fun case1() {
    val d = Delegated(Impl())
    checkSubtype<Int>(d.f())
    checkSubtype<DefaultFn>(d)
    checkSubtype<Delegated>(d)
}

fun case2() {
    val d = Delegated(OverrideImpl())
    checkSubtype<Int>(d.f())
    checkSubtype<DefaultFn>(d)
}

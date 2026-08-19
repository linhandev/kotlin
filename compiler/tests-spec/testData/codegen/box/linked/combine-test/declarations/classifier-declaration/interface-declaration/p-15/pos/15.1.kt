// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 15 -> sentence 15
 *                inheritance, overriding -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: multi-interface default function conflict resolved by override + super<IF>; runtime returns sum of both defaults
 */

// TESTCASE NUMBER: 1
interface DefaultA {
    fun f(): Int = 1
}

interface DefaultB {
    fun f(): Int = 2
}

class ResolveWithSuper : DefaultA, DefaultB {
    override fun f(): Int = super<DefaultA>.f() + super<DefaultB>.f()
}

class OnlyA : DefaultA

class OnlyB : DefaultB

fun box(): String {
    if (OnlyA().f() != 1) return "NOK: only-a-default"
    if (OnlyB().f() != 2) return "NOK: only-b-default"
    if (ResolveWithSuper().f() != 3) return "NOK: resolved-sum"
    val asA: DefaultA = ResolveWithSuper()
    if (asA.f() != 3) return "NOK: via-a-type"
    val asB: DefaultB = ResolveWithSuper()
    if (asB.f() != 3) return "NOK: via-b-type"
    return "OK"
}

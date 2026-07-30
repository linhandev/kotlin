// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 21 -> sentence 21
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: class delegation forwards interface default function body from delegatee at runtime
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

fun box(): String {
    if (Impl().f() != 1) return "NOK: direct-default"
    if (Delegated(Impl()).f() != 1) return "NOK: delegated-default"
    val viaInterface: DefaultFn = Delegated(Impl())
    if (viaInterface.f() != 1) return "NOK: via-interface-default"
    if (Delegated(OverrideImpl()).f() != 2) return "NOK: delegated-override"
    val viaOverride: DefaultFn = Delegated(OverrideImpl())
    if (viaOverride.f() != 2) return "NOK: via-interface-override"
    return "OK"
}

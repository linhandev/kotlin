// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 1 -> sentence 1
 *                declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: implementing class inherits interface default function body without explicit override
 */

// TESTCASE NUMBER: 1
interface DefaultFn {
    fun f(): Int = 1
}

class InheritDefault : DefaultFn

class ExplicitOverride : DefaultFn {
    override fun f(): Int = 2
}

fun viaClass(): Int = InheritDefault().f()

fun viaInterface(i: DefaultFn = InheritDefault()): Int = i.f()

fun box(): String {
    if (viaClass() != 1) return "NOK: inherit-default"
    if (viaInterface() != 1) return "NOK: via-interface-default"
    if (viaInterface(ExplicitOverride()) != 2) return "NOK: via-interface-override"
    if (ExplicitOverride().f() != 2) return "NOK: explicit-override"
    return "OK"
}

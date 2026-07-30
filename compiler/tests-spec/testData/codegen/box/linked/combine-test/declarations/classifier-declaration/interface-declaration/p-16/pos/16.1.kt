// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 16 -> sentence 16
 *                inheritance, overriding -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: override can call interface default via super<IF>; runtime returns default plus offset
 */

// TESTCASE NUMBER: 1
interface WithDefault {
    fun f(): Int = 1
}

class InheritDefault : WithDefault

class WrapDefault : WithDefault {
    override fun f(): Int = super<WithDefault>.f() + 10
}

fun box(): String {
    if (InheritDefault().f() != 1) return "NOK: inherit-default"
    if (WrapDefault().f() != 11) return "NOK: super-plus-ten"
    val asIface: WithDefault = WrapDefault()
    if (asIface.f() != 11) return "NOK: dynamic-dispatch"
    return "OK"
}

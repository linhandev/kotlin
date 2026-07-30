// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 2 -> sentence 2
 *                inheritance, overriding -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: implementing class can override interface default function body; runtime dispatches to override
 */

// TESTCASE NUMBER: 1
interface DefaultFn {
    fun f(): Int = 1
}

class UseDefault : DefaultFn

class OverrideDefault : DefaultFn {
    override fun f(): Int = 2
}

fun box(): String {
    if (UseDefault().f() != 1) return "NOK: default"
    if (OverrideDefault().f() != 2) return "NOK: override"
    val asIface: DefaultFn = OverrideDefault()
    if (asIface.f() != 2) return "NOK: dynamic-dispatch"
    return "OK"
}

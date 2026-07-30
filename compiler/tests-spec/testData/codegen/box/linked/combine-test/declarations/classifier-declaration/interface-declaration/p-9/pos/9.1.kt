// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 9 -> sentence 9
 *                inheritance, overriding -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: implementing class can override interface val that has a default getter body
 */

// TESTCASE NUMBER: 1
interface Sized {
    val size: Int get() = 0
}

class KeepDefault : Sized

class OverrideSize : Sized {
    override val size: Int get() = 10
}

fun box(): String {
    if (KeepDefault().size != 0) return "NOK: default"
    if (OverrideSize().size != 10) return "NOK: override"
    val asIface: Sized = OverrideSize()
    if (asIface.size != 10) return "NOK: dynamic-dispatch"
    return "OK"
}

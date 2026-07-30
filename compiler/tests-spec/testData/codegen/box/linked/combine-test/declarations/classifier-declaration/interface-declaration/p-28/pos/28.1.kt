// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 28 -> sentence 28
 *                declarations, classifier-declaration, object-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: object declaration implementing interface inherits default function body (contrast with p-25 empty class)
 */

// TESTCASE NUMBER: 1
interface WithDefault {
    fun f(): Int = 1
    fun tag(): String = "default"
}

object InheritDefault : WithDefault

object OverrideDefault : WithDefault {
    override fun f(): Int = 2
}

fun box(): String {
    if (InheritDefault.f() != 1) return "NOK: object-default-f"
    if (InheritDefault.tag() != "default") return "NOK: object-default-tag"
    if (OverrideDefault.f() != 2) return "NOK: object-override-f"
    if (OverrideDefault.tag() != "default") return "NOK: object-override-keeps-tag"
    val asIface: WithDefault = InheritDefault
    if (asIface.f() != 1) return "NOK: via-interface-default"
    val asIface2: WithDefault = OverrideDefault
    if (asIface2.f() != 2) return "NOK: via-interface-override"
    if (InheritDefault !is WithDefault) return "NOK: is-interface"
    return "OK"
}

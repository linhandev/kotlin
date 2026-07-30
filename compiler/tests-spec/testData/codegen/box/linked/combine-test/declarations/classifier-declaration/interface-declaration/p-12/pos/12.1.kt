// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 12 -> sentence 12
 *                inheritance, overriding -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: sub-interface can override parent interface default function; implementing class inherits the overridden default
 */

// TESTCASE NUMBER: 1
interface BaseDefault {
    fun f(): Int = 1
}

interface DerivedOverride : BaseDefault {
    override fun f(): Int = 2
}

class ViaDerived : DerivedOverride

class DirectBase : BaseDefault

fun box(): String {
    if (DirectBase().f() != 1) return "NOK: parent-default"
    if (ViaDerived().f() != 2) return "NOK: derived-default"
    val asBase: BaseDefault = ViaDerived()
    if (asBase.f() != 2) return "NOK: via-base-type"
    val asDerived: DerivedOverride = ViaDerived()
    if (asDerived.f() != 2) return "NOK: via-derived-type"
    return "OK"
}

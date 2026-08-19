// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 27 -> sentence 27
 *                declarations, property-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: interface default function can read same-interface abstract property provided by implementing class (class-body override; contrast with p-7 primary ctor and p-26 impl-only field)
 */

// TESTCASE NUMBER: 1
interface Doubler {
    val n: Int
    fun double(): Int = n * 2
}

class BodyOverride : Doubler {
    override val n: Int = 3
}

class GetterOverride : Doubler {
    override val n: Int get() = 5
}

fun box(): String {
    if (BodyOverride().n != 3) return "NOK: body-n"
    if (BodyOverride().double() != 6) return "NOK: body-double"
    if (GetterOverride().n != 5) return "NOK: getter-n"
    if (GetterOverride().double() != 10) return "NOK: getter-double"
    val asIface: Doubler = BodyOverride()
    if (asIface.double() != 6) return "NOK: via-interface"
    val asIface2: Doubler = GetterOverride()
    if (asIface2.double() != 10) return "NOK: via-interface-getter"
    return "OK"
}

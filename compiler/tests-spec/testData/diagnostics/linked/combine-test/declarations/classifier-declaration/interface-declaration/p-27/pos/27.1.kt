// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 27 -> sentence 27
 *                declarations, property-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: type inference when interface default function reads same-interface abstract property from implementing class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Doubler {
    val n: Int
    fun double(): Int = n * 2
}

class BodyOverride : Doubler {
    override val n: Int = 3
}

fun case1() {
    val c = BodyOverride()
    checkSubtype<BodyOverride>(c)
    checkSubtype<Doubler>(c)
    checkSubtype<Int>(c.n)
    checkSubtype<Int>(c.double())
}

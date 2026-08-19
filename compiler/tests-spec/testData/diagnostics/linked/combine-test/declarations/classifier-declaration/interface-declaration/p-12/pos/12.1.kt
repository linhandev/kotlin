// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 12 -> sentence 12
 *                inheritance, overriding -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: sub-interface overriding parent interface default function type inference in implementing class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface BaseDefault {
    fun f(): Int = 1
}

interface DerivedOverride : BaseDefault {
    override fun f(): Int = 2
}

class ViaDerived : DerivedOverride

fun case1() {
    val c = ViaDerived()
    checkSubtype<ViaDerived>(c)
    checkSubtype<Int>(c.f())
    checkSubtype<DerivedOverride>(c)
    checkSubtype<BaseDefault>(c)
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: implementing class inherits sub-interface overridden default function type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface ChainA {
    fun f(): String = "A"
}

interface ChainB : ChainA {
    override fun f(): String = "B"
}

class ChainImpl : ChainB

fun case1() {
    val c = ChainImpl()
    checkSubtype<ChainImpl>(c)
    checkSubtype<String>(c.f())
    checkSubtype<ChainB>(c)
    checkSubtype<ChainA>(c)
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: implementing class inherits the latest default from sub-interface after parent default is overridden in the chain
 */

// TESTCASE NUMBER: 1
interface ChainA {
    fun f(): String = "A"
}

interface ChainB : ChainA {
    override fun f(): String = "B"
}

class ChainImpl : ChainB

class DirectA : ChainA

fun box(): String {
    if (DirectA().f() != "A") return "NOK: root-default"
    if (ChainImpl().f() != "B") return "NOK: chain-default"
    val asRoot: ChainA = ChainImpl()
    if (asRoot.f() != "B") return "NOK: via-root-type"
    val asMid: ChainB = ChainImpl()
    if (asMid.f() != "B") return "NOK: via-mid-type"
    return "OK"
}

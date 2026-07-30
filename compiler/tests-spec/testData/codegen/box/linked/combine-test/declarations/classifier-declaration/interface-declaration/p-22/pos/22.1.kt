// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 22 -> sentence 22
 *                declarations, declaration-visibility -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: interface default function body can call private helper member within the same interface
 */

// TESTCASE NUMBER: 1
interface WithPrivateHelper {
    fun f(): Int = helper() + 10
    private fun helper(): Int = 1
}

class InheritHelper : WithPrivateHelper

class OverrideF : WithPrivateHelper {
    override fun f(): Int = 42
}

fun box(): String {
    if (InheritHelper().f() != 11) return "NOK: default-via-private-helper"
    if (OverrideF().f() != 42) return "NOK: override-public-only"
    val viaInterface: WithPrivateHelper = InheritHelper()
    if (viaInterface.f() != 11) return "NOK: via-interface"
    return "OK"
}

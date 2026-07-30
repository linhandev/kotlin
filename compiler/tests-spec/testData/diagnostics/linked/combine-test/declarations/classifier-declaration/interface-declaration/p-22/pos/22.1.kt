// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 22 -> sentence 22
 *                declarations, declaration-visibility -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: type inference when interface default function body calls private helper within the same interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface WithPrivateHelper {
    fun f(): Int = helper() + 10
    private fun helper(): Int = 1
}

class InheritHelper : WithPrivateHelper

fun case1() {
    val c = InheritHelper()
    checkSubtype<Int>(c.f())
    checkSubtype<WithPrivateHelper>(c)
    checkSubtype<InheritHelper>(c)
}

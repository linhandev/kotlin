// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 28 -> sentence 28
 *                declarations, classifier-declaration, object-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: type inference when object declaration inherits interface default function body
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface WithDefault {
    fun f(): Int = 1
    fun tag(): String = "default"
}

object InheritDefault : WithDefault

fun case1() {
    checkSubtype<InheritDefault>(InheritDefault)
    checkSubtype<WithDefault>(InheritDefault)
    checkSubtype<Int>(InheritDefault.f())
    checkSubtype<String>(InheritDefault.tag())
}

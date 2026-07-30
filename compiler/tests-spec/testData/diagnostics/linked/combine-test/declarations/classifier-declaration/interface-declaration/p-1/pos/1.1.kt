// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 1 -> sentence 1
 *                declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: implementing class inherits interface default function body type inference without explicit override
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface DefaultFn {
    fun f(): Int = 1
}

class InheritDefault : DefaultFn

fun case1() {
    val c = InheritDefault()
    checkSubtype<InheritDefault>(c)
    checkSubtype<Int>(c.f())
    checkSubtype<DefaultFn>(c)
}

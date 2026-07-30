// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 2 -> sentence 2
 *                inheritance, overriding -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: overriding interface default function body type inference in implementing class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface DefaultFn {
    fun f(): Int = 1
}

class OverrideDefault : DefaultFn {
    override fun f(): Int = 2
}

fun case1() {
    val c = OverrideDefault()
    checkSubtype<OverrideDefault>(c)
    checkSubtype<Int>(c.f())
    checkSubtype<DefaultFn>(c)
}

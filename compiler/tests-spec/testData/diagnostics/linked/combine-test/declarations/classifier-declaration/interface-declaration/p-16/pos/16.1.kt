// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 16 -> sentence 16
 *                inheritance, overriding -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: type inference when override calls interface default via super<IF>
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface WithDefault {
    fun f(): Int = 1
}

class WrapDefault : WithDefault {
    override fun f(): Int = super<WithDefault>.f() + 10
}

fun case1() {
    val c = WrapDefault()
    checkSubtype<WrapDefault>(c)
    checkSubtype<Int>(c.f())
    checkSubtype<WithDefault>(c)
}

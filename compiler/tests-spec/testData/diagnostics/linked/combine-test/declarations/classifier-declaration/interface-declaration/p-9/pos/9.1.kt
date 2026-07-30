// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 9 -> sentence 9
 *                inheritance, overriding -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: overriding interface val with default getter body type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Sized {
    val size: Int get() = 0
}

class OverrideSize : Sized {
    override val size: Int get() = 10
}

fun case1() {
    val c = OverrideSize()
    checkSubtype<OverrideSize>(c)
    checkSubtype<Int>(c.size)
    checkSubtype<Sized>(c)
}

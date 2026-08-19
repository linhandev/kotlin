// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 4 -> sentence 4
 *                declarations, classifier-declaration, class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: interface default function body calling other same-interface members type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface NestedDefaults {
    fun base(): Int = 1
    fun wrap(): Int = base() + 1
}

class C : NestedDefaults

fun case1() {
    val c = C()
    checkSubtype<C>(c)
    checkSubtype<Int>(c.base())
    checkSubtype<Int>(c.wrap())
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 4 -> sentence 4
 *                declarations, classifier-declaration, class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: interface default function body can call other members of the same interface
 */

// TESTCASE NUMBER: 1
interface NestedDefaults {
    fun base(): Int = 1
    fun wrap(): Int = base() + 1
}

class C : NestedDefaults

class OverrideBase : NestedDefaults {
    override fun base(): Int = 10
}

fun box(): String {
    if (C().base() != 1) return "NOK: base-default"
    if (C().wrap() != 2) return "NOK: wrap-calls-base"
    if (OverrideBase().wrap() != 11) return "NOK: wrap-calls-overridden-base"
    return "OK"
}

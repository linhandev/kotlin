// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: object can be assigned to an interface-typed reference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Svc {
    fun run(): Int
}

object Engine : Svc {
    override fun run(): Int = 7
}

fun case_1(s: Svc = Engine) {
    checkSubtype<Svc>(Engine)
    checkSubtype<Svc>(s)
    checkSubtype<Int>(s.run())
}

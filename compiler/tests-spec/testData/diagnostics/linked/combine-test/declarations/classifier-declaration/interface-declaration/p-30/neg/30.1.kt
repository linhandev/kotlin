// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, open-classes -> paragraph 30 -> sentence 30
 *                inheritance, overriding -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: final is illegal on interface members (WRONG_MODIFIER_CONTAINING_DECLARATION) because interface members are open/overridable by default; explicit open on abstract is only REDUNDANT_OPEN_IN_INTERFACE (warning, not error)
 */

// TESTCASE NUMBER: 1
interface ModalityOnInterface {
    // Members are open by default — final cannot seal an interface default body.
    <!WRONG_MODIFIER_CONTAINING_DECLARATION!>final<!> fun sealedDefault(): Int = 1

    // Default body without modality keyword remains overridable (contrast).
    fun openByDefault(): Int = 2
}

class Impl : ModalityOnInterface {
    override fun openByDefault(): Int = 20
}

// FIR_IDENTICAL
// LANGUAGE: +FunctionalInterfaceConversion
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 20 -> sentence 20
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: fun interface cannot declare more than one abstract member even when an additional default function body is present (FUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS; contrast with p-19)
 */

// TESTCASE NUMBER: 1
// Default body does not relax the single-abstract-member rule (interaction with default implementations).
<!FUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS!>fun<!> interface TwoAbstractMembers {
    fun a()
    fun b()
    fun label(): String = "run"
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: interface default function body cannot reference implementing-class state field (UNRESOLVED_REFERENCE; contrast with p-27 reading same-interface abstract property)
 */

// TESTCASE NUMBER: 1
interface DefaultSeesImplField {
    fun f(): Int = <!UNRESOLVED_REFERENCE!>field<!>
}

class HasField : DefaultSeesImplField {
    val field = 1
}

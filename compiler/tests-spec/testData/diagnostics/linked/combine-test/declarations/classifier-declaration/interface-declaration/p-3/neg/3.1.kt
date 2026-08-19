// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 3 -> sentence 3
 *                declarations, classifier-declaration, class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: implementing class must provide body when interface member has no default function body
 */

// TESTCASE NUMBER: 1
interface AbstractFn {
    fun f(): Int
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class MissingImpl<!> : AbstractFn

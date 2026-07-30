// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 6 -> sentence 6
 *                declarations, classifier-declaration, class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: implementing class must provide abstract interface val when no default accessor body exists
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class User<!> : Named

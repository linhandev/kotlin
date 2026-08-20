// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 89 -> sentence 89
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 89 -> sentence 89
 *                declarations, property-declaration -> paragraph 89 -> sentence 89
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 89 -> sentence 89
 * NUMBER: 1
 * DESCRIPTION: interface property overridden in primary constructor
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

class User(override val name: String) : Named

fun case1() {
    checkSubtype<String>(User("Ann").name)
}

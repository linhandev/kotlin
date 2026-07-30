// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 76 -> sentence 76
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 76 -> sentence 76
 * NUMBER: 1
 * DESCRIPTION: abstract class may declare primary constructor parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class Shape(val name: String)

class Circle : Shape("c")

fun test(): String = Circle().name

fun case1() {
    checkSubtype<String>(test())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 9 -> sentence 9
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: subtype as to Base is legal and infers Base
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base
class Sub : Base()

fun case1() {
    val s = Sub()
    checkSubtype<Base>(s as Base)
}

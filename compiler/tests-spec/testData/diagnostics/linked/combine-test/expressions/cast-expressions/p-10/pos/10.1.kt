// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 10 -> sentence 10
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Base as Sub infers Sub
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base
class Sub : Base()

fun case1(b: Base) {
    checkSubtype<Sub>(b as Sub)
}

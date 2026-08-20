// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -INCOMPATIBLE_MODIFIERS
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 400 -> sentence 400
 * declarations, declaration-visibility -> paragraph 400 -> sentence 400
 * declarations, function-declaration -> paragraph 400 -> sentence 400
 * NUMBER: 1
 * DESCRIPTION: subclass cannot implement parent private abstract fun
 */

// TESTCASE NUMBER: 1
abstract class A { private abstract fun core(): Int; fun api(): Int = core() }
class Impl : A() { <!CANNOT_OVERRIDE_INVISIBLE_MEMBER!>override<!> fun core(): Int = 1 }
fun test() = Impl().api()

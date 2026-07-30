// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: abstract class upper bound allows calling abstract members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class Base { abstract fun id(): Int }

class Holder<T : Base>(val t: T) { fun run() = t.id() }

class Impl : Base() { override fun id() = 1 }

fun test(): Int = Holder(Impl()).run()

fun case1() {
    checkSubtype<Int>(test())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: KClass equality of Box Int and Box String due to erasure infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>

fun test(): Boolean = Box<Int>()::class == Box<String>()::class

fun case1() {
    checkSubtype<Boolean>(test())
}

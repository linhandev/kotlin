// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 396 -> sentence 396
 * declarations, declaration-visibility -> paragraph 396 -> sentence 396
 * declarations, function-declaration -> paragraph 396 -> sentence 396
 * declarations, classifier-declaration, companion-object -> paragraph 396 -> sentence 396
 * NUMBER: 1
 * DESCRIPTION: instance member can call companion private fun through public companion member type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Host { fun go(): Int = Companion.api(); companion object { private fun secret(): Int = 2; fun api(): Int = secret() } }

// TESTCASE NUMBER: 1
fun test(): Int = Host().go()

fun case1() {
    checkSubtype<Int>(test())
}

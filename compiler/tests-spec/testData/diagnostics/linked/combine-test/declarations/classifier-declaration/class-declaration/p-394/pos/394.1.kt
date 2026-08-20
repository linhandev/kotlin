// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 394 -> sentence 394
 * declarations, declaration-visibility -> paragraph 394 -> sentence 394
 * declarations, function-declaration -> paragraph 394 -> sentence 394
 * declarations, classifier-declaration, companion-object -> paragraph 394 -> sentence 394
 * NUMBER: 1
 * DESCRIPTION: companion private fun is accessible only through public companion or in-class entry type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Host { companion object { private fun secret(): Int = 1; fun api(): Int = secret() } }

// TESTCASE NUMBER: 1
fun test(): Int = Host.api()

fun case1() {
    checkSubtype<Int>(test())
}

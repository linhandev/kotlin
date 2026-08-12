// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 390 -> sentence 390
 * declarations, declaration-visibility -> paragraph 390 -> sentence 390
 * declarations, function-declaration -> paragraph 390 -> sentence 390
 * NUMBER: 1
 * DESCRIPTION: internal fun is callable outside class within module unlike private
 */

// TESTCASE NUMBER: 1
class C { internal fun api(): Int = 1 }

// TESTCASE NUMBER: 1
fun test(): Int = C().api()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

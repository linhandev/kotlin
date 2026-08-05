// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 351 -> sentence 351
 * declarations, declaration-visibility -> paragraph 351 -> sentence 351
 * declarations, property-declaration -> paragraph 351 -> sentence 351
 * NUMBER: 1
 * DESCRIPTION: 类体 private val 仅类内可读
 */

// TESTCASE NUMBER: 1
class C { private val secret = 1; fun get(): Int = secret }

// TESTCASE NUMBER: 1
fun test(): Int = C().get()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 405 -> sentence 405
 * declarations, declaration-visibility -> paragraph 405 -> sentence 405
 * declarations, function-declaration -> paragraph 405 -> sentence 405
 * inheritance, inheriting -> paragraph 405 -> sentence 405
 * NUMBER: 1
 * DESCRIPTION: subclass can call parent protected fun
 */

// TESTCASE NUMBER: 1
open class Base { protected fun prot(): Int = 1 }

// TESTCASE NUMBER: 1
class Sub : Base() { fun read(): Int = prot() }

// TESTCASE NUMBER: 1
fun test(): Int = Sub().read()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

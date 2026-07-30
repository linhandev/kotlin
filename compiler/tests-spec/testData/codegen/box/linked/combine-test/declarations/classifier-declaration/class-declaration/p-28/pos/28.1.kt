// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: upper bound Base allows calling Base members
 */

// TESTCASE NUMBER: 1
open class Base { fun f() = 1 }

class Gen<T : Base>(val t: T) { fun run() = t.f() }

fun test(): Int = Gen(Base()).run()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

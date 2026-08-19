// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: final class as upper bound is legal
 */

// TESTCASE NUMBER: 1
class Final

class Wrap<T : Final>(val t: T)

fun test(): Final = Wrap(Final()).t

fun box(): String {
    if (test()::class != Final::class) return "NOK"
    return "OK"
}

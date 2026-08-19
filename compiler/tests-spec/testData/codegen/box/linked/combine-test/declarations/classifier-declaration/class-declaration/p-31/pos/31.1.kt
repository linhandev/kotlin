// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: class and member function type parameters combine in map
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T) { fun <R> map(f: (T) -> R): R = f(v) }

fun test(): String = Box(1).map { it.toString() }

fun box(): String {
    if (test() != "1") return "NOK"
    return "OK"
}

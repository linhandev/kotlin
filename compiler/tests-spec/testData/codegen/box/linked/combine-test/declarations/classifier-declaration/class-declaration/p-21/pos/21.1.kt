// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 21 -> sentence 21
 *                type-system, introduction-1 -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: nullable type argument Box String question
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T)

fun test(): String? = Box<String?>(null).v

fun box(): String {
    if (test() != null) return "NOK"
    return "OK"
}

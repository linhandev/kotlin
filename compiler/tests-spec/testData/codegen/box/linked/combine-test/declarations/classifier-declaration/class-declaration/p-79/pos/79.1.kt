// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 79 -> sentence 79
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 79 -> sentence 79
 *                declarations, property-declaration -> paragraph 79 -> sentence 79
 *                declarations, declarations-with-type-parameters -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: generic class primary constructor val retains type parameter
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test(): Int = Box(1).value


fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

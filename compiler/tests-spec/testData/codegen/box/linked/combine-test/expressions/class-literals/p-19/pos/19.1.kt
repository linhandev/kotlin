// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: primitive array class literal IntArray::class differs from generic Array<Int>::class, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = IntArray::class != Array<Int>::class

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}

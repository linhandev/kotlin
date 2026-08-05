// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 402 -> sentence 402
 * declarations, declaration-visibility -> paragraph 402 -> sentence 402
 * declarations, function-declaration -> paragraph 402 -> sentence 402
 * declarations, function-declaration -> paragraph 402 -> sentence 402
 * NUMBER: 1
 * DESCRIPTION: private inline fun 可在类内调用
 */

// TESTCASE NUMBER: 1
class C { private inline fun work(block: () -> Int): Int = block(); fun test(): Int = work { 2 } }

// TESTCASE NUMBER: 1
fun test(): Int = C().test()

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
